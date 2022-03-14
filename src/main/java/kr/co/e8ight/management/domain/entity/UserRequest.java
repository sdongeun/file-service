package kr.co.e8ight.management.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "user_requests"
)
public class UserRequest {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "request_id",
            nullable = false
    )
    private int id;
    @Column(
            name = "file_type",
            columnDefinition = "varchar(255)"
    )
    private String fileType;
    @Column(
            name = "user_id"
    )
    private int userId;
    @Column(
            name = "request_status"
    )
    private String requestStatus;
    @Column(
            name = "request_date"
    )
    private LocalDateTime requestDate;
    @Column(
            name = "result"
    )
    private String result;

    public UserRequest() {
        this.requestDate = LocalDateTime.now();
    }

    public UserRequest(int id, String fileType, int userId, String requestStatus, LocalDateTime requestDate, String result) {
        this.id = id;
        this.fileType = fileType;
        this.userId = userId;
        this.requestStatus = requestStatus;
        this.requestDate = requestDate;
        this.result = result;
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

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRequestStatus() {
        return this.requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDateTime getRequestDate() {
        return this.requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
