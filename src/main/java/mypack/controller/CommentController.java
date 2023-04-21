//package mypack.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import mypack.payload.BaseResponse;
//import mypack.payload.comment.CommentRequest;
//import mypack.service.CommentService;
//import mypack.service.UserDetailsCustom;
//
//@RestController
//@RequestMapping("api/comment")
//public class CommentController {
//
//    @Autowired
//    CommentService commentService;
//
//    @PostMapping
//    public ResponseEntity<?> comment(@AuthenticationPrincipal UserDetailsCustom user,
//            @RequestBody CommentRequest req) {
//        if (user == null)
//            return ResponseEntity.ok(new BaseResponse(false, "Login to comment !"));
//        return ResponseEntity
//                .ok(commentService.comment(user.getEmail(), req.getPostId(), req.getReplyId(), req.getContent()));
//    }
//
//    @GetMapping("{postId}")
//    public ResponseEntity<?> getComment(@AuthenticationPrincipal UserDetailsCustom user,
//            @PathVariable("postId") Long postId) {
//        return ResponseEntity
//                .ok(commentService.getPostComment(postId));
//    }
//
//}
