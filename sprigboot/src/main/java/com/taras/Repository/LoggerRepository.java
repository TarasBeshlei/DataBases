package com.taras.Repository;

import com.taras.domain.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggerRepository extends JpaRepository<Logger, Long> {
    List<Logger> findByStudentLike(String like);
}
