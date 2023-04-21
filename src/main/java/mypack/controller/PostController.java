package mypack.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.service.EmployerPostService;
import mypack.service.PostSearchService;
import mypack.utility.ModelSorting;
import mypack.utility.Page;
import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EGender;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EMostViewType;
import mypack.utility.datatype.EPosition;

@RequestMapping("api/post")
@RestController
public class PostController {

	@Autowired
	EmployerPostService postService;

	@Autowired
	PostSearchService postSearchService;

	@GetMapping("/{postId}")
	public ResponseEntity<?> getPostDetail(@PathVariable("postId") Long postId) {
		return ResponseEntity.ok(postService.getDetail(postId));
	}

	@GetMapping("hot-job")
	public ResponseEntity<?> getHotJob(@RequestParam(name = "page", required = true) Integer page,
			@RequestParam(name = "limit", required = true) Integer limit) {

		return ResponseEntity.ok(postSearchService.getHotJob(page, limit));
	}

	@GetMapping("most-view")
	public ResponseEntity<?> getMostView(@RequestParam(name = "type", required = true) EMostViewType type,
			@RequestParam(name = "page", required = true) Integer page,
			@RequestParam(name = "limit", required = true) Integer limit) {

		return ResponseEntity.ok(postSearchService.getMostView(type, page, limit));
	}

	@GetMapping
	public ResponseEntity<?> getPost(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "recruit", required = false) Long recruit,
			@RequestParam(name = "salary", required = false) Long salary,
			@RequestParam(name = "method", required = false) EMethod method,
			@RequestParam(name = "position", required = false) EPosition position,
			@RequestParam(name = "experience", required = false) EExperience experience,
			@RequestParam(name = "gender", required = false) EGender gender,
			@RequestParam(name = "currency", required = false) ECurrency currency,
			@RequestParam(name = "authorId", required = false) Long authorId,
			@RequestParam(name = "industryId", required = false) Long industryId,
			@RequestParam(name = "cityId", required = false) Long cityId,
			@RequestParam(name = "expirationDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date expirationDate,
			@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(name = "serviceId", required = false) Long serviceId,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "limit", required = false) Integer limit,
			@RequestParam(required = false) Integer sortBy, @RequestParam(required = false) Boolean sortDescending)
			throws ParseException {

		Long count = postSearchService.getCountBeforSearch(keyword, recruit, salary, method, position, experience,
				gender, currency, authorId, industryId, cityId, null, expirationDate, startDate, serviceId);

		return ResponseEntity.ok(postSearchService.search(keyword, recruit, salary, method, position, experience,
				gender, currency, authorId, industryId, cityId, null, expirationDate, startDate, serviceId,
				new Page(page, limit, count.intValue(), ModelSorting.getPostSort(sortBy, sortDescending))));
	}

}
