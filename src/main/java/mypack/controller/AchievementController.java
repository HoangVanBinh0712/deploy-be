package mypack.controller;

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

import com.cloudinary.http44.api.Response;

import mypack.payload.jobseeker.AchievementRequest;
import mypack.service.AchievementService;
import mypack.service.UserDetailsCustom;
import mypack.utility.datatype.EAchievementType;

@RestController
@RequestMapping("api")
public class AchievementController {

    @Autowired
    AchievementService achievementService;

    @GetMapping("employer/view-jsk-profiles")
    public ResponseEntity<?> viewUserProfile(@AuthenticationPrincipal UserDetailsCustom employer,
            @RequestParam(name = "user", required = true) Long userId,
            @RequestParam(name = "type", required = true) EAchievementType type) {
        return ResponseEntity.ok(achievementService.getListByEmployer(employer.getEmail(), userId, type));
    }

    @GetMapping("user/achievements")
    public ResponseEntity<?> viewUserProfile(@AuthenticationPrincipal UserDetailsCustom user,
            @RequestParam(name = "type", required = false) EAchievementType type) {
        return ResponseEntity.ok(achievementService.getListByOwner(user.getId(), type));
    }

    @PostMapping(value = "user/achievements", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createAchievements(@AuthenticationPrincipal UserDetailsCustom user,
            @RequestPart("info") @Valid AchievementRequest request,
            @RequestPart(name = "image", required = false) MultipartFile file) {
        return ResponseEntity.ok(achievementService.create(user.getId(), request, file));
    }

    @PutMapping(value = "user/achievements/{achId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAchievements(@AuthenticationPrincipal UserDetailsCustom user,
            @PathVariable("achId") Long achId,
            @RequestPart("info") @Valid AchievementRequest request,
            @RequestPart(name = "image", required = false) MultipartFile file) {
        return ResponseEntity.ok(achievementService.update(user.getId(), achId, request, file));
    }

    @DeleteMapping("user/achievements/{achId}")
    public ResponseEntity<?> deleteAchievements(@AuthenticationPrincipal UserDetailsCustom user,
            @PathVariable("achId") Long achId) {
        return ResponseEntity.ok(achievementService.delete(user.getId(), achId));
    }
}
