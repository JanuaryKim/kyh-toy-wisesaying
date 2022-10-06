package kyh.toy.wisesaying.member.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v13/members")
@RestController
public class MemberController {

    @GetMapping
    public ResponseEntity getMembers() {

        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
