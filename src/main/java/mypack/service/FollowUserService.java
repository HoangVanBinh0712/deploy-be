package mypack.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.FollowedUserDTO;
import mypack.dto.FollowerUserDTO;
import mypack.model.FollowUser;
import mypack.model.User;
import mypack.payload.BaseResponse;
import mypack.repository.FollowUserRepository;
import mypack.repository.UserRepository;
import mypack.utility.datatype.ERole;

@Service
public class FollowUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowUserRepository followUserRepository;

    @Autowired
    ModelMapper mapper;

    public BaseResponse follow(String jobseekerEmail, Long empId) {
        Optional<User> user = userRepository.findByEmailAndRole(jobseekerEmail, ERole.ROLE_USER);
        if (user.isEmpty())
            throw new CommonRuntimeException("Only allow JobSeeker to follow Employer !");
        Optional<User> optEmployer = userRepository.findById(empId);
        if (optEmployer.isEmpty() || optEmployer.get().getRole() != ERole.ROLE_EMPLOYER)
            throw new CommonRuntimeException("Only allow JobSeeker to follow Employer !");
        Optional<FollowUser> temp = followUserRepository.findByUserAndFollower(optEmployer.get(), user.get());
        if (temp.isEmpty()) {
            FollowUser fl = new FollowUser(null, optEmployer.get(), user.get(), new Date());
            followUserRepository.save(fl);
        }

        return new BaseResponse(true, "Follow success !");
    }

    public BaseResponse unFollow(String jobseekerEmail, Long empId) {
        Optional<User> user = userRepository.findByEmailAndRole(jobseekerEmail, ERole.ROLE_USER);
        if (user.isEmpty())
            throw new CommonRuntimeException("Only allow JobSeeker to unfollow Employer !");
        Optional<User> optEmployer = userRepository.findById(empId);
        if (optEmployer.isEmpty() || optEmployer.get().getRole() != ERole.ROLE_EMPLOYER)
            throw new CommonRuntimeException("Only allow JobSeeker to unfollow Employer !");
        Optional<FollowUser> fl = followUserRepository.findByUserAndFollower(optEmployer.get(), user.get());
        if (fl.isPresent()) {
            followUserRepository.delete(fl.get());
        }
        return new BaseResponse(true, "Unfollow success !");
    }

    public Long countEmpFollowers(Long id) {
        Optional<User> optEmployer = userRepository.findById(id);
        if (optEmployer.isEmpty() || optEmployer.get().getRole() != ERole.ROLE_EMPLOYER)
            throw new CommonRuntimeException("Employer not found with Id: " + id);
        Long count = followUserRepository.countFollowUserById(id);

        return count;
    }

    public List<FollowerUserDTO> getEmpFollowers(Long id) {
        Optional<User> optEmployer = userRepository.findById(id);
        if (optEmployer.isEmpty() || optEmployer.get().getRole() != ERole.ROLE_EMPLOYER)
            throw new CommonRuntimeException("Employer not found with Id: " + id);
        List<FollowUser> fl = followUserRepository.getAllFollowers(id);

        return fl.stream().map(x -> mapper.map(x, FollowerUserDTO.class)).toList();
    }

    public List<FollowedUserDTO> getAllFollowedEmployer(String jobseekerEmail) {
        Optional<User> user = userRepository.findByEmailAndRole(jobseekerEmail, ERole.ROLE_USER);
        if (user.isEmpty())
            throw new CommonRuntimeException("Only allow Jobseeker ! ");

        List<FollowUser> fl = followUserRepository.getAllFollowedEmployer(user.get().getId());
        return fl.stream().map(x -> mapper.map(x, FollowedUserDTO.class)).toList();
    }
}
