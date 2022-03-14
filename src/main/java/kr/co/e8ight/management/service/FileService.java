package kr.co.e8ight.management.service;

import kr.co.e8ight.management.dto.DownloadFileResponseDto;
import kr.co.e8ight.management.dto.UploadFileResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    int checkDuplicateFiles(String checksum);

    UploadFileResponseDto storeFile(MultipartFile file, int userId, String fileType, String checksum);

    DownloadFileResponseDto loadFileAsResource(int userId, String fileType) throws Exception;
}
