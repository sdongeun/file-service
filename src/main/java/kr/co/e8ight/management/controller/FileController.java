package kr.co.e8ight.management.controller;

import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import kr.co.e8ight.management.dto.DownloadFileResponseDto;
import kr.co.e8ight.management.dto.UploadFileResponseDto;
import kr.co.e8ight.management.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"api"})
public class FileController {
    @Autowired
    FileService fileService;

    public FileController() {
    }

    @ApiOperation(
            value = "",
            notes = "파일 중복 확인 "
    )
    @GetMapping({"/check"})
    public int checkFileExist(@RequestParam("checksum") String checksum) {
        return this.fileService.checkDuplicateFiles(checksum);
    }

    @ApiOperation(
            value = "",
            notes = "파일 업로드 "
    )
    @PostMapping({"/upload"})
    public UploadFileResponseDto uploadFile(@RequestPart(name = "file",required = false) MultipartFile file, @RequestParam("userId") int userId, @RequestParam("fileType") String fileType, @RequestParam("checksum") String checksum) {
        return this.fileService.storeFile(file, userId, fileType, checksum);
    }

    @ApiOperation(
            value = "",
            notes = "파일 다운로드"
    )
    @GetMapping({"/download"})
    public ResponseEntity<Resource> downloadFile(@RequestParam("userId") int userId, @RequestParam(name = "fileType",required = false) String fileType, HttpServletRequest request) {
        DownloadFileResponseDto response = null;

        try {
            response = this.fileService.loadFileAsResource(userId, fileType);
        } catch (Exception var8) {
            return ResponseEntity.notFound().build();
        }

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(response.getResource().getFile().getAbsolutePath());
        } catch (IOException var7) {
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ((BodyBuilder)ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header("Content-Disposition", new String[]{"attachment; filename=\"" + response.getOriginFileName() + "\""})).body(response.getResource());
    }
}
