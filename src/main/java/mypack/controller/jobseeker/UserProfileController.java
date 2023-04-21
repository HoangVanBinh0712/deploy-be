package mypack.controller.jobseeker;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mypack.payload.BaseResponse;
import mypack.payload.JobseekerCV.CVUploadRequest;
import mypack.payload.auth.JobseekerProfileUpdateRequest;
import mypack.payload.auth.PasswordUpdateRequest;
import mypack.service.CVSearchAndViewedService;
import mypack.service.UserDetailsCustom;
import mypack.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {

	@Autowired
	UserService userService;

	@Autowired
	CVSearchAndViewedService cvSearchAndViewedService;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> update(@AuthenticationPrincipal UserDetailsCustom user,
			@RequestPart("info") @Valid JobseekerProfileUpdateRequest request,
			@RequestPart(name = "avatar", required = false) MultipartFile avatar,
			@RequestPart(name = "cover", required = false) MultipartFile cover) {
		return ResponseEntity.ok(userService.jobseekerUpdate(request, avatar, cover, user.getId()));
	}

	@GetMapping
	public ResponseEntity<?> getUserInformation(@AuthenticationPrincipal UserDetailsCustom user) {
		return ResponseEntity.ok(userService.getUserProfile(user.getEmail()));
	}

	@PutMapping(value = "/password")
	public ResponseEntity<?> changePassword(@AuthenticationPrincipal UserDetailsCustom user,
			@RequestBody @Valid PasswordUpdateRequest request) {
		return ResponseEntity.ok(userService.changePassword(user.getEmail(), request));
	}

	//
	@PostMapping(value = "/cv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadCV(@AuthenticationPrincipal UserDetailsCustom user,
			@RequestPart(name = "CV") MultipartFile cv, @RequestPart(name = "name") CVUploadRequest request) {
		if (cv.getContentType() != null && !cv.getContentType().equals(MediaType.APPLICATION_PDF_VALUE))
			return ResponseEntity.ok(new BaseResponse(false, "Allow only pdf file !"));
		return ResponseEntity.ok(userService.uploadCV(user.getEmail(), cv, request));
	}

	@PutMapping(value = "/cv/{mediaId}")
	public ResponseEntity<?> uploadCV(@AuthenticationPrincipal UserDetailsCustom user,
			@PathVariable("mediaId") Long mediaId, @RequestBody CVUploadRequest request) {

		return ResponseEntity.ok(userService.updateCV(user.getEmail(), mediaId, request));
	}

	@DeleteMapping(value = "/cv")
	public ResponseEntity<?> uploadCV(@AuthenticationPrincipal UserDetailsCustom user,
			@RequestParam("mediaId") Long mediaId) {
		return ResponseEntity.ok(userService.deleteCV(user.getId(), mediaId));
	}

	@GetMapping(value = "/cv")
	public ResponseEntity<?> getListCV(@AuthenticationPrincipal UserDetailsCustom user) {
		return ResponseEntity.ok(userService.getListCV(user.getId()));
	}

	@GetMapping(value = "/cv/{id}")
	public ResponseEntity<?> getCVDetail(@AuthenticationPrincipal UserDetailsCustom user,
			@PathVariable("id") Long mediaId) {
		return ResponseEntity.ok(userService.getCVDetail(user.getId(), mediaId));
	}

	@GetMapping("list-company")
	public ResponseEntity<?> getListCompany() {
		return ResponseEntity.ok(userService.getListCompany());
	}

	@GetMapping("highlight-company")
	public ResponseEntity<?> getHighLightCompany() {
		return ResponseEntity.ok(userService.getHighLightCompany());
	}

	@GetMapping("count-cv-viewer/{mediaId}")
	public ResponseEntity<?> getCountViewerCV(@AuthenticationPrincipal UserDetailsCustom user,
			@PathVariable("mediaId") Long mediaIa) {
		return ResponseEntity.ok(cvSearchAndViewedService.getCountViewForCV(user.getId(), mediaIa));
	}

	@GetMapping("cv-viewer/{mediaId}")
	public ResponseEntity<?> getViewerCV(@AuthenticationPrincipal UserDetailsCustom user,
			@PathVariable("mediaId") Long mediaIa) {
		return ResponseEntity.ok(cvSearchAndViewedService.getEmployerViewedCV(user.getId(), mediaIa));
	}
}
