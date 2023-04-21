package mypack.controller.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.http44.api.Response;

import mypack.service.EmployerStatisticService;
import mypack.service.UserDetailsCustom;

@RestController
@RequestMapping("/api/employer/statistic")
public class EmployerStatisticController {

    @Autowired
    EmployerStatisticService service;

    @GetMapping("view-page")
    public ResponseEntity<?> getViewPageStatistic(@AuthenticationPrincipal UserDetailsCustom emp,
            @RequestParam(name = "year", required = true) Integer year) {

        return ResponseEntity.ok(service.getViewPageCountStatistc(emp.getEmail(), year));

    }

    @GetMapping("cv-submit")
    public ResponseEntity<?> getCVSubmitStatistic(@AuthenticationPrincipal UserDetailsCustom emp,
            @RequestParam(name = "year", required = true) Integer year) {

        return ResponseEntity.ok(service.getCVSubmitCountStatistc(emp.getEmail(), year));

    }

    @GetMapping("view-post")
    public ResponseEntity<?> getViewPostStatistic(@AuthenticationPrincipal UserDetailsCustom emp) {

        return ResponseEntity.ok(service.getViewPostCountStatistc(emp.getEmail()));

    }

//    @GetMapping("comments")
//    public ResponseEntity<?> getCommentStatistic(@AuthenticationPrincipal UserDetailsCustom emp,
//            @RequestParam(name = "year", required = true) Integer year) {
//
//        return ResponseEntity.ok(service.getCountComments(emp.getEmail(), year));
//
//    }
}
