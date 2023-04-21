package mypack.controller.auth;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.payload.auth.ResetPasswordRequest;
import mypack.service.SendEmailService;
import mypack.service.UserService;
import mypack.utility.datatype.EmailType;

@RestController
@RequestMapping("api")
public class SendVerifyCodeController {

	@Autowired
	SendEmailService sendEmailService;

	@Autowired
	UserService userService;

	@GetMapping("/send-user-verify-code")
	public ResponseEntity<?> sendUserCode(@NotBlank String email) {
		return ResponseEntity.ok(sendEmailService.sendVerifyAndResetPasswordCode(email, EmailType.CONFIRM_EMAIL));
	}

	@PostMapping("/confirm-email")
	public ResponseEntity<?> confirmEmailUser(@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "code", required = true) String code) {
		return ResponseEntity.ok(userService.emailConfirm(email, code));
	}

	@GetMapping("/send-reset-password-code")
	public ResponseEntity<?> sendUserRPCode(@NotBlank String email) {
		return ResponseEntity.ok(sendEmailService.sendVerifyAndResetPasswordCode(email, EmailType.RESET_PASSWORD));
	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> userResetPasswordWithCode(@RequestBody @Valid ResetPasswordRequest request) {
		return ResponseEntity.ok(userService.changePasswordWithResetCode(request));
	}

}
