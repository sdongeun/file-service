package kr.co.e8ight.management.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(
        prefix = "file"
)
@Entity
@Table(
        name = "files"
)
public class FileEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "file_id"
    )
    private int id;
    @Column(
            name = "file_type",
            columnDefinition = "varchar(255)"
    )
    private String fileType;
    @Column(
            name = "file_name",
            columnDefinition = "varchar(255)"
    )
    private String fileName;
    @Column(
            name = "file_extension"
    )
    private String fileExtension;
    @Column(
            name = "file_format"
    )
    private String fileFormat;
    @Column(
            name = "upload_dir"
    )
    private String uploadDir;
    @Column(
            name = "upload_file_name"
    )
    private String uploadFileName;
    @Column(
            name = "checksum"
    )
    private String checksum;
    @Column(
            name = "create_date"
    )
    private LocalDateTime createDate;
    @Column(
            name = "revision"
    )
    private int revision;

    public FileEntity() {
        this.createDate = LocalDateTime.now();
    }

    public FileEntity(int id, String fileType, String fileName, String fileExtension, String fileFormat, String uploadDir, String uploadFileName, String checksum, LocalDateTime createDate, int revision) {
        this.id = id;
        this.fileType = fileType;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileFormat = fileFormat;
        this.uploadDir = uploadDir;
        this.uploadFileName = uploadFileName;
        this.checksum = checksum;
        this.createDate = createDate;
        this.revision = revision;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileFormat() {
        return this.fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getUploadDir() {
        return this.uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getUploadFileName() {
        return this.uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getChecksum() {
        return this.checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public int getRevision() {
        return this.revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }
}
