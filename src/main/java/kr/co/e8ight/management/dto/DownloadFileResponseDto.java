package kr.co.e8ight.management.dto;

import org.springframework.core.io.Resource;

public class DownloadFileResponseDto {
    private Resource resource;
    private String originFileName;

    public DownloadFileResponseDto() {
    }

    public DownloadFileResponseDto(Resource resource, String originFileName) {
        this.resource = resource;
        this.originFileName = originFileName;
    }

    public Resource getResource() {
        return this.resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getOriginFileName() {
        return this.originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String toString() {
        return "DownloadFileResponseDto [resource=" + this.resource + ", originFileName=" + this.originFileName + "]";
    }
}
