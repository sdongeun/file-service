package kr.co.e8ight.management.domain.repository;

import kr.co.e8ight.management.domain.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer> {
    @Query(nativeQuery = true, value="SELECT count(*) FROM files f WHERE checksum = ?1")
    int getDuplicateFilesByCheckSum(String checksum);

    @Query(nativeQuery = true, value="SELECT * FROM files f WHERE file_name = ?1")
    FileEntity getFileByFileName(String fileName);

    @Query(nativeQuery = true, value = "SELECT * FROM files WHERE checksum = ?1 ORDER BY revision DESC LIMIT 1")
    FileEntity getLatestFileByChecksum(String checksum);

    @Query(nativeQuery = true, value = "SELECT * FROM files f WHERE file_type = ?1  ORDER BY revision DESC LIMIT 1")
    FileEntity getLatestFileByFileType(String fileType);


}
