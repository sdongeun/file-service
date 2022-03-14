package kr.co.e8ight.management.domain.repository;

import kr.co.e8ight.management.domain.entity.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Integer> {
}
