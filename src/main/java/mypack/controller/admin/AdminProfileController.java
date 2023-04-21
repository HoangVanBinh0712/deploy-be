package mypack.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mypack.service.UserDetailsCustom;
import mypack.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminProfileController {
	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<?> getAdminInformation(@AuthenticationPrincipal UserDetailsCustom user) {
		return ResponseEntity.ok(userService.getAdminProfile(user.getId()));
	}
}
