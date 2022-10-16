package kyh.toy.wisesaying.member.repository;

import kyh.toy.wisesaying.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findMemberByEmail(String email);
}
