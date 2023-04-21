package mypack.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.AchievementDTO;
import mypack.model.Achievement;
import mypack.model.MediaResource;
import mypack.model.User;
import mypack.payload.BaseResponse;
import mypack.payload.DataResponse;
import mypack.payload.jobseeker.AchievementRequest;
import mypack.repository.AchievementRepository;
import mypack.repository.UserRepository;
import mypack.utility.datatype.EAchievementType;
import mypack.utility.datatype.ERole;

@Service
public class AchievementService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AchievementRepository achievementRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    MediaResourceService mediaResourceService;

    // who ever also create for their own profile
    @Transactional
    public DataResponse<AchievementDTO> create(Long userId, AchievementRequest request, MultipartFile file) {
        Achievement ach = mapper.map(request, Achievement.class);
        ach.setUser(userRepository.getReferenceById(userId));
        ach.setCreateDate(new Date());
        if (file != null) {
            // Up load new Image
            try {
                MediaResource img = mediaResourceService.save(file.getBytes());
                ach.setImage(img);
            } catch (IOException e) {
                throw new CommonRuntimeException("Error occur when uploading new image !");
            }
        }
        return new DataResponse<>(true, "Success", mapper.map(achievementRepository.save(ach), AchievementDTO.class));
    }

    // update
    @Transactional
    public DataResponse<AchievementDTO> update(Long userId, Long achId, AchievementRequest request,
            MultipartFile file) {

        Optional<Achievement> ach = achievementRepository.findById(achId);
        if (ach.isEmpty())
            throw new CommonRuntimeException("Achievement not found !");
        Achievement achievement = ach.get();
        if (achievement.getUser().getId() != userId) {
            throw new CommonRuntimeException("You do not have permission !");
        }
        achievement.setName(request.getName());
        achievement.setUrl(request.getUrl());
        achievement.setType(request.getType());

        if (file != null) {
            // delete old image
            if (achievement.getImage() != null) {
                achievementRepository.updateBeforeDeleteImage(achId);
                if (!mediaResourceService.delete(achievement.getImage().getId()))
                    throw new CommonRuntimeException("Error occur when deleting old image !");
            }
            // Up load new Image
            try {
                MediaResource img = mediaResourceService.save(file.getBytes());
                achievement.setImage(img);
            } catch (IOException e) {
                throw new CommonRuntimeException("Error occur when uploading new image !");
            }
        }
        return new DataResponse<>(true, "Success",
                mapper.map(achievementRepository.save(achievement), AchievementDTO.class));
    }

    @Transactional
    public BaseResponse delete(Long userId, Long achId) {
        Optional<Achievement> ach = achievementRepository.findById(achId);
        if (ach.isEmpty())
            throw new CommonRuntimeException("Achievement not found !");
        Achievement achievement = ach.get();
        if (achievement.getUser().getId() != userId) {
            throw new CommonRuntimeException("You do not have permission !");
        }
        achievementRepository.delete(achievement);

        if (achievement.getImage() != null)
            mediaResourceService.delete(achievement.getImage().getId());
        return new BaseResponse(true, "Delete success !");
    }

    public List<AchievementDTO> getListByOwner(Long userId, EAchievementType type) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new CommonRuntimeException("User not found !");
        List<AchievementDTO> res = new ArrayList<>();
        if (type != null) {
            res = achievementRepository.findByUserAndType(user.get(), type).stream()
                    .map(x -> mapper.map(x, AchievementDTO.class)).toList();

        } else {
            res = achievementRepository.findByUser(user.get()).stream()
                    .map(x -> mapper.map(x, AchievementDTO.class)).toList();
        }
        return res;
    }

    // Employer
    public List<AchievementDTO> getListByEmployer(String empEmail, Long userId, EAchievementType type) {
        Optional<User> emp = userRepository.findByEmailAndRole(empEmail, ERole.ROLE_EMPLOYER);
        if (emp.isEmpty())
            throw new CommonRuntimeException("Access denied ! Become employer to see further features !");
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new CommonRuntimeException("User not found !");

        List<AchievementDTO> res = new ArrayList<>();
        res = achievementRepository.findByUserAndType(user.get(), type).stream()
                .map(x -> mapper.map(x, AchievementDTO.class)).toList();
        return res;
    }
}
