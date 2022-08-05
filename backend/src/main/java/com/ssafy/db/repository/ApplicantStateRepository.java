package com.ssafy.db.repository;

import com.ssafy.db.entity.ApplicantState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantStateRepository extends JpaRepository<ApplicantState,Long> {
}
