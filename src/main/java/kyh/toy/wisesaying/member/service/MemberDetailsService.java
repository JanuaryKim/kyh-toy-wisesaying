package kyh.toy.wisesaying.member.service;


import kyh.toy.wisesaying.jwt.JwtTokenizer;
import kyh.toy.wisesaying.member.entity.Member;
import kyh.toy.wisesaying.utils.CustomAuthorityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;


@RequiredArgsConstructor
@Component
public class MemberDetailsService implements UserDetailsService {

    private final MemberService memberService;
    private final CustomAuthorityUtil customAuthorityUtil;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberService.verifyExistMember(username);


        return new UserDetailsMember(member);
    }

    private class UserDetailsMember extends Member implements UserDetails{

        public UserDetailsMember(Member member) {
            setMemberId(member.getMemberId());
            setEmail(member.getEmail());
            setName(member.getName());
            setPassword(member.getPassword());
            setRoles(member.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return customAuthorityUtil.convertAuthority(this.getRoles());
        }

        @Override
        public String getUsername() {
            return this.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        //계정 정보가 잠겨있는지 체크하는 메소드, 해당 메소드에서 false를 리턴하면 AuthenticationProvider에서 크레덴셜 검증전에
        //해당 UserDetails를 가진 Authentication가 검사가 가능한지에서 불가능한것으로 체크가 되므로 크레덴셜 검증 조차 하지 못한다.
        //한마디로 잠겨있지 않지?라고 체크하는 용도이다.
        //나머지 is~ 메소드들 실질적인 크레덴셜 검증 전에 체크가 가능한지 묻는 메소드이기 때문에 모두 true를 리턴해야함
        @Override
        public boolean isAccountNonLocked() {
            return true;
        }


        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
