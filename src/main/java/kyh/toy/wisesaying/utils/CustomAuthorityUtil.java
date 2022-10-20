package kyh.toy.wisesaying.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthorityUtil {

    private final List<String> USER_ROLE = List.of("USER");
    private final List<String> ADMIN_ROLE = List.of("USER","ADMIN");

    @Value("${admin.email}")
    private String adminEmail;

    public List<String> createRoles(String email)
    {
        if (email.equals(adminEmail)) {
            return ADMIN_ROLE;
        }

        return USER_ROLE;
    }

    public List<GrantedAuthority> convertAuthority(List<String> authority) {

        return authority.stream().map(auth-> new SimpleGrantedAuthority(auth)).collect(Collectors.toList());
    }

}
