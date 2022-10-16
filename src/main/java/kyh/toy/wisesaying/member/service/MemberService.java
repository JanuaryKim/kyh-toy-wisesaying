package kyh.toy.wisesaying.member.service;


import kyh.toy.wisesaying.exception.BusinessException;
import kyh.toy.wisesaying.exception.ErrorCode;
import kyh.toy.wisesaying.member.entity.Member;
import kyh.toy.wisesaying.member.repository.MemberRepository;
import kyh.toy.wisesaying.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository repository;
    private final CustomBeanUtils<Member> beanUtils;

    /** ------------------------- 핸들러 메소드 연결됨 -------------------------**/

    public Member createMember(Member member) {

        verifyNotExistEmail(member.getEmail());

        Member savedMember = repository.save(member);


        return savedMember;
    }


    public Member findMember(Long memberId) {

        Member member = getMember(memberId);

        return member;
    }

    public void eraseMember(Long memberId) {

        //존재하지 않는다면 에러
        verifyExistMember(memberId);

        repository.deleteById(memberId);
    }

    public Member updateMember(Member member) {

        Member findMember = verifyExistMember(member.getMemberId());
        Member modifiedMember = beanUtils.copyNotNullProperties(findMember, member);

        Member updatedMember = repository.save(modifiedMember);

        return updatedMember;
    }

    public Page<Member> findMembers(int size, int page) {

        //Pageable 이 최상위 인터페이스, PageRequest는 하위 구현체
        Pageable pageRequest = PageRequest.of(page, size, Sort.by("memberId").descending());
        Page<Member> memberPage = repository.findAll(pageRequest);


        return memberPage;
    }


    /** ------------------------- 핸들러 메소드 연결됨 ------------------------- **/

    //없는 이메일임을 검증한다
    private void verifyNotExistEmail(String email) {

        repository.findMemberByEmail(email).ifPresent(
                member -> {throw new BusinessException(ErrorCode.ALREADY_MEMBER_EXISTS);});
    }

    //혹시 있는 멤버인가 검증. 있으면 에러
    private void verifyNotExistEmail(Long memberId) {

        repository.findById(memberId).ifPresent(
                member -> {throw new BusinessException(ErrorCode.ALREADY_MEMBER_EXISTS);});
    }

    private Member getMember(Long memberId) {
        Member member = verifyExistMember(memberId);
        return member;
    }

    //있는 멤버임을 검증
    private Member verifyExistMember(Long memberId) {
        return repository.findById(memberId).orElseThrow(
                ()-> {throw new BusinessException(ErrorCode.MEMBER_NOT_EXISTS);});
    }
}
