package mypack.controller.employer;

import java.text.ParseException;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.payload.post.PostRequest;
import mypack.service.EmployerPostService;
import mypack.service.PostSearchService;
import mypack.service.UserDetailsCustom;
import mypack.utility.ModelSorting;
import mypack.utility.Page;
import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EGender;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;
// import mypack.service.PostService;
// import mypack.service.auth.UserDetailsCustom;
// import mypack.utility.ModelSorting;
// import mypack.utility.Page;
import mypack.utility.datatype.EStatus;

@RequestMapping("api/employer/post")
@RestController
public class EmployerPostController {
	@Autowired
	EmployerPostService postService;

	@Autowired
	PostSearchService postSearchService;

	@PostMapping
	public ResponseEntity<?> createPost(@AuthenticationPrincipal UserDetailsCustom emp,
			@RequestBody @Valid PostRequest request) {
		return ResponseEntity.ok(postService.create(emp.getId(), request));
	}

	@PutMapping("/{postId}")
	public ResponseEntity<?> updatePost(@AuthenticationPrincipal UserDetailsCustom emp,
			@PathVariable("postId") Long postId,
			@RequestBody @Valid PostRequest request) {
		return ResponseEntity.ok(postService.update(emp.getId(), postId, request));
	}

	@PutMapping
	public ResponseEntity<?> changePostStatus(@AuthenticationPrincipal UserDetailsCustom emp,
			@RequestParam("postId") Long postId,
			@RequestParam("status") EStatus status) {

		return ResponseEntity.ok(postService.changePostStatus(emp.getId(), postId, status));
	}

	@GetMapping
	public ResponseEntity<?> getPost(@AuthenticationPrincipal UserDetailsCustom emp,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "recruit", required = false) Long recruit,
			@RequestParam(name = "salary", required = false) Long salary,
			@RequestParam(name = "method", required = false) EMethod method,
			@RequestParam(name = "position", required = false) EPosition position,
			@RequestParam(name = "experience", required = false) EExperience experience,
			@RequestParam(name = "gender", required = false) EGender gender,
			@RequestParam(name = "currency", required = false) ECurrency currency,
			@RequestParam(name = "industryId", required = false) Long industryId,
			@RequestParam(name = "cityId", required = false) Long cityId,
			@RequestParam(name = "expirationDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date expirationDate,
			@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(name = "serviceId", required = false) Long serviceId,
			@RequestParam(name = "status", required = false) EStatus status,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "limit", required = false) Integer limit,
			@RequestParam(required = false) Integer sortBy,
			@RequestParam(required = false) Boolean sortDescending)
			throws ParseException {

		Long count = postSearchService.getCountBeforSearch(keyword, recruit, salary, method, position, experience,
				gender, currency, emp.getId(), industryId, cityId, status, expirationDate, startDate,
				serviceId);

		return ResponseEntity.ok(postSearchService.search(keyword, recruit, salary, method, position, experience,
				gender, currency, emp.getId(), industryId, cityId, status, expirationDate, startDate,
				serviceId, new Page(page, limit, count.intValue(), ModelSorting.getPostSort(sortBy, sortDescending))));
	}

	@GetMapping("{postId}")
	public ResponseEntity<?> getDetail(@AuthenticationPrincipal UserDetailsCustom emp,
			@PathVariable("postId") Long postId) {
		return ResponseEntity.ok(postService.getEmployerPostDetail(emp.getId(), postId));
	}
}
