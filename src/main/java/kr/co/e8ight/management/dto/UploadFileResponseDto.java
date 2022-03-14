package kr.co.e8ight.management.dto;

public class UploadFileResponseDto {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;

    public UploadFileResponseDto() {
    }

    public UploadFileResponseDto(String fileName, String fileDownloadUri, String fileType) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return this.fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String toString() {
        return "UploadFileResponseDto [fileName=" + this.fileName + ", fileDownloadUri=" + this.fileDownloadUri + ", fileType=" + this.fileType + "]";
    }
}
