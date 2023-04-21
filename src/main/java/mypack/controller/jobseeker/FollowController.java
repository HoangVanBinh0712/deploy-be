package mypack.controller.jobseeker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mypack.service.FollowService;
import mypack.service.FollowUserService;
import mypack.service.UserDetailsCustom;

@RestController
@RequestMapping("api/user")
public class FollowController {

    @Autowired
    FollowService followService;

    @GetMapping("follow")
    public ResponseEntity<?> getPostFollowed(@AuthenticationPrincipal UserDetailsCustom user) {
        return ResponseEntity.ok(followService.getListPostFollowed(user.getEmail()));
    }

    @PostMapping("follow/{postId}")
    public ResponseEntity<?> followPost(@AuthenticationPrincipal UserDetailsCustom user,
            @PathVariable("postId") Long postId) {
        return ResponseEntity.ok(followService.followPost(user.getEmail(), postId, true));
    }

    @PostMapping("unfollow/{postId}")
    public ResponseEntity<?> unFollowPost(@AuthenticationPrincipal UserDetailsCustom user,
            @PathVariable("postId") Long postId) {
        return ResponseEntity.ok(followService.followPost(user.getEmail(), postId, false));
    }

    @Autowired
    FollowUserService followUserService;

    @GetMapping("list-emp-follow")
    public ResponseEntity<?> getEmpFollowed(@AuthenticationPrincipal UserDetailsCustom user) {
        return ResponseEntity.ok(followUserService.getAllFollowedEmployer(user.getEmail()));
    }

    @PostMapping("emp-follow/{empId}")
    public ResponseEntity<?> followEmp(@AuthenticationPrincipal UserDetailsCustom user,
            @PathVariable("empId") Long empId) {
        return ResponseEntity.ok(followUserService.follow(user.getEmail(), empId));
    }

    @PostMapping("emp-unfollow/{empId}")
    public ResponseEntity<?> unFollowEmp(@AuthenticationPrincipal UserDetailsCustom user,
            @PathVariable("empId") Long empId) {
        return ResponseEntity.ok(followUserService.unFollow(user.getEmail(), empId));
    }
}
