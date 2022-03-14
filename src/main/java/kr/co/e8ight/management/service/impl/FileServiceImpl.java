package kr.co.e8ight.management.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import kr.co.e8ight.management.domain.entity.FileEntity;
import kr.co.e8ight.management.domain.entity.UserRequest;
import kr.co.e8ight.management.domain.repository.FileRepository;
import kr.co.e8ight.management.domain.repository.UserRequestRepository;
import kr.co.e8ight.management.dto.DownloadFileResponseDto;
import kr.co.e8ight.management.dto.UploadFileResponseDto;
import kr.co.e8ight.management.exception.FileUploadException;
import kr.co.e8ight.management.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FileServiceImpl implements FileService {
    private final Path fileStorageLocation;
    @Autowired
    FileRepository fileRepository;
    @Autowired
    UserRequestRepository userRequestRepository;

    @Autowired
    public FileServiceImpl(FileEntity file) {
        this.fileStorageLocation = Paths.get(file.getUploadDir()).toAbsolutePath().normalize();

        try {
            System.out.println("파일 레포지토리 생성 !! 경로 : " + this.fileStorageLocation);
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception var3) {
            throw new FileUploadException("Could not create the directory where the uploaded files will be stored.", var3);
        }
    }

    @Transactional(
            readOnly = true
    )
    public int checkDuplicateFiles(String checksum) {
        return this.fileRepository.getDuplicateFilesByCheckSum(checksum);
    }

    @Transactional
    public UploadFileResponseDto storeFile(MultipartFile file, int userId, String fileType, String checksum) {
        UploadFileResponseDto response = new UploadFileResponseDto();

        try {
            String requestResult = "";
            FileEntity newFile = new FileEntity();
            newFile.setFileType(fileType);
            newFile.setChecksum(checksum);
            String uploadFileName;
            FileEntity latestFileInfo;
            if (file != null) {
                System.out.println("중복 파일 없음! ");
                String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
                if (originalFileName.contains("..")) {
                    throw new FileUploadException("Sorry! Filename contains invalid path sequence : " + originalFileName);
                }

                String fileExtension = "";

                try {
                    fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
                } catch (Exception var12) {
                    fileExtension = "";
                }

                uploadFileName = UUID.randomUUID().toString();
                if (this.fileRepository.getFileByFileName(uploadFileName) != null) {
                    uploadFileName = UUID.randomUUID().toString();
                }

                Path targetLocation = this.fileStorageLocation.resolve(uploadFileName + "." + fileExtension);
                newFile.setFileName(originalFileName.substring(0, originalFileName.lastIndexOf(".")));
                newFile.setFileExtension(fileExtension);
                newFile.setFileFormat(file.getContentType());
                newFile.setUploadDir(targetLocation.toString());
                newFile.setUploadFileName(uploadFileName);
                Files.copy(file.getInputStream(), targetLocation, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
                requestResult = "File Upload Success";
            } else {
                System.out.println("중복 파일 있음! ");
                latestFileInfo = this.fileRepository.getLatestFileByChecksum(checksum);
                newFile.setFileName(latestFileInfo.getFileName());
                newFile.setFileExtension(latestFileInfo.getFileExtension());
                newFile.setFileFormat(latestFileInfo.getFileFormat());
                newFile.setUploadDir(latestFileInfo.getUploadDir());
                newFile.setUploadFileName(latestFileInfo.getUploadFileName());
                newFile.setCreateDate(latestFileInfo.getCreateDate());
                requestResult = "Duplicated File Exist";
            }

            latestFileInfo = this.fileRepository.getLatestFileByFileType(fileType);
            if (latestFileInfo != null) {
                newFile.setRevision(latestFileInfo.getRevision() + 1);
            } else {
                newFile.setRevision(1);
            }

            this.fileRepository.save(newFile);
            UserRequest userRequest = new UserRequest();
            userRequest.setFileType(fileType);
            userRequest.setUserId(userId);
            userRequest.setRequestStatus("Upload");
            userRequest.setResult(requestResult);
            this.userRequestRepository.save(userRequest);
            uploadFileName = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(newFile.getFileName()).toUriString();
            response.setFileName(newFile.getFileName());
            response.setFileDownloadUri(uploadFileName);
            response.setFileType(fileType);
            return response;
        } catch (IOException var13) {
            throw new FileUploadException("Could not store file Please try again!", var13);
        }
    }

    @Transactional
    public DownloadFileResponseDto loadFileAsResource(int userId, String fileType) throws Exception {
        DownloadFileResponseDto response = new DownloadFileResponseDto();

        try {
            FileEntity fileInfo = this.fileRepository.getLatestFileByFileType(fileType);
            Path filePath = this.fileStorageLocation.resolve(fileInfo.getUploadFileName() + "." + fileInfo.getFileExtension()).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                UserRequest request = new UserRequest();
                request.setFileType(fileType);
                request.setUserId(userId);
                request.setRequestStatus("Download");
                request.setResult("File Donwload Success");
                this.userRequestRepository.save(request);
                response.setResource(resource);
                response.setOriginFileName(URLEncoder.encode(fileInfo.getFileName() + "." + fileInfo.getFileExtension(), "UTF-8").replaceAll("\\+", "%20"));
                return response;
            } else {
                throw new FileNotFoundException("File not found " + fileType);
            }
        } catch (IOException var8) {
            throw new FileNotFoundException("File not found " + fileType);
        }
    }
}
