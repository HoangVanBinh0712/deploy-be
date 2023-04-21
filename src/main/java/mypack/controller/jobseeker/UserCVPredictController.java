package mypack.controller.jobseeker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.service.CVPredictService;
import mypack.service.UserDetailsCustom;

@RestController
@RequestMapping("api")
public class UserCVPredictController {

	@Autowired
	CVPredictService cvPredictService;

	@GetMapping("/jobseeker/cvpredict")
	public ResponseEntity<?> getCVPredict(@AuthenticationPrincipal UserDetailsCustom user,
			@RequestParam("mediaId") Long mediaId) {
		return ResponseEntity.ok(cvPredictService.predict(mediaId, user.getId()));
	}

	@GetMapping("/user/cvpredict/post")
	public ResponseEntity<?> getPostPredictedForCV(@RequestParam("industryId") Long industryId,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "limit", required = false) Integer limit) {
		return ResponseEntity.ok(cvPredictService.getPostByIndustry(industryId, page, limit));
	}

}