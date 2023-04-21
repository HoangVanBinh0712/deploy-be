package mypack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.model.Post;
import mypack.model.User;
import mypack.payload.statistic.StatisticForCount;
import mypack.repository.CVSubmitRepository;
//import mypack.repository.CommentRepository;
import mypack.repository.PostRepository;
import mypack.repository.UserRepository;
import mypack.repository.ViewPageRepository;
import mypack.utility.datatype.ERole;

@Service
public class EmployerStatisticService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    PostRepository postRepo;

    @Autowired
    ViewPageRepository viewPageRepository;

    @Autowired
    CVSubmitRepository cvSubmitRepository;
//    @Autowired
//    CommentRepository commentRepository;

    public List<StatisticForCount> getViewPageCountStatistc(String empEmail, Integer year) {
        Optional<User> user = userRepo.findByEmailAndRole(empEmail, ERole.ROLE_EMPLOYER);
        if (user.isEmpty())
            throw new CommonRuntimeException("User not found with email: " + empEmail);
        // Count total view Page
        List<StatisticForCount> vpc = viewPageRepository.getCountViewPage(user.get(), year);
        if (vpc.isEmpty())
            throw new CommonRuntimeException(
                    "Not enough data to statistic . Try to post a job then try a gain later !");
        return vpc;
    }

    public Long getViewPostCountStatistc(String empEmail) {
        Optional<User> user = userRepo.findByEmailAndRole(empEmail, ERole.ROLE_EMPLOYER);
        if (user.isEmpty())
            throw new CommonRuntimeException("User not found with email: " + empEmail);
        // Count total view post
        Long res = postRepo.getTotalViewPost(user.get().getId());
        if (res == null)
            res = 0L;
        return res;
    }

    public List<StatisticForCount> getCVSubmitCountStatistc(String empEmail, Integer year) {
        Optional<User> user = userRepo.findByEmailAndRole(empEmail, ERole.ROLE_EMPLOYER);
        if (user.isEmpty())
            throw new CommonRuntimeException("User not found with email: " + empEmail);
        // Count total view Page
        List<Post> lstId = postRepo.getEmpListPost(user.get().getId());

        List<StatisticForCount> vpc = cvSubmitRepository.getCountCVSubmit(lstId, year);
        if (vpc.isEmpty())
            throw new CommonRuntimeException(
                    "Not enough data to statistic . Try to post a job then try a gain later !");
        return vpc;
    }

//    public List<StatisticForCount> getCountComments(String empEmail, Integer year) {
//
//        Optional<User> user = userRepo.findByEmailAndRole(empEmail, ERole.ROLE_EMPLOYER);
//        if (user.isEmpty())
//            throw new CommonRuntimeException("User not found with email: " + empEmail);
//        // Count total view Page
//        List<Post> lstId = postRepo.getEmpListPost(user.get().getId());
//
//        List<StatisticForCount> vpc = commentRepository.getCountComments(lstId, year);
//
//        if (vpc.isEmpty())
//            throw new CommonRuntimeException(
//                    "Not enough data to statistic . Try to post a job then try a gain later !");
//
//        return vpc;
//    }
}
