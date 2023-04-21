package mypack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.payload.BaseResponse;
import mypack.service.NotificationService;
import mypack.service.UserDetailsCustom;

@RestController
@RequestMapping("api/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping
    public ResponseEntity<?> getNotification(@AuthenticationPrincipal UserDetailsCustom user,
            @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        if (user == null)
            return ResponseEntity.status(401).body(
                    new BaseResponse(false, "Login to get your notification !"));
        return ResponseEntity.ok(notificationService.getListNotification(user.getId(), page, limit));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteNotification(@AuthenticationPrincipal UserDetailsCustom user,
            @RequestParam("notiId") Long notiId) {
        if (user == null)
            return ResponseEntity.status(401).body(
                    new BaseResponse(false, "Login to delete your notification !"));
        return ResponseEntity.ok(notificationService.deleteNotification(user.getId(), notiId));
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteComment(@AuthenticationPrincipal UserDetailsCustom user) {
        return ResponseEntity
                .ok(notificationService.deleteAllNotification(user.getId()));
    }
}
