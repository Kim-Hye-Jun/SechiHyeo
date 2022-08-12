package com.ssafy.backend.db.repository;

import com.ssafy.backend.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Member findByLoginId(String loginId);

    @Query(value = "SELECT COUNT(*) FROM member WHERE login_id = :loginId",
            nativeQuery = true)
    Long getCountOfLoginID(String loginId);

    @Query(value = "SELECT COUNT(*) FROM member WHERE phone_number = :phoneNumber",
            nativeQuery = true)
    Long getCountOfPhoneNumber(String phoneNumber);

    @Query(value = "SELECT COUNT(*) FROM member WHERE email = :email",
            nativeQuery = true)
    Long getCountOfEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM member WHERE nickname = :nickname",
            nativeQuery = true)
    Long getCountOfNickname(String nickname);

}
