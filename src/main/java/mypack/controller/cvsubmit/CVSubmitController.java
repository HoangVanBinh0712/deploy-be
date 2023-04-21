package mypack.controller.cvsubmit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.payload.jobseeker.CVSubmitRequest;
import mypack.service.CVSubmitService;
import mypack.service.UserDetailsCustom;

@RestController
@RequestMapping("api")
public class CVSubmitController {

	@Autowired
	CVSubmitService cvSubmitService;

	@PostMapping("user/submitcv")
	public ResponseEntity<?> submit(@AuthenticationPrincipal UserDetailsCustom user,@RequestBody @Valid CVSubmitRequest request) {
		return ResponseEntity.ok(cvSubmitService.submitCV(user, request));
	}

	@DeleteMapping("user/submitcv")
	public ResponseEntity<?> cancelSubmit(@AuthenticationPrincipal UserDetailsCustom user,
			@RequestParam("postId") Long postId, @RequestParam("mediaId") Long mediaId) {
		return ResponseEntity.ok(cvSubmitService.deleteSubmittedCV(user.getId(), postId, mediaId));
	}

	@GetMapping("user/submitcv/{mediaId}")
	public ResponseEntity<?> getPostSubmittedForCv(@AuthenticationPrincipal UserDetailsCustom user,
			@PathVariable("mediaId") Long mediaId) {
		return ResponseEntity.ok(cvSubmitService.getSubmittedPostForCV(user.getId(), mediaId));
	}

	@GetMapping("employer/submitcv")
	public ResponseEntity<?> getSubmittedCv(@AuthenticationPrincipal UserDetailsCustom emp,
			@RequestParam("postId") Long postId, @RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "limit", required = false) Integer limit) {
		return ResponseEntity.ok(cvSubmitService.getListCV(emp.getId(), postId, page, limit));
	}
}
