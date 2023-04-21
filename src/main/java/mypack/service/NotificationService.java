package mypack.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.NotificationDTO;
import mypack.model.Notification;
import mypack.model.Post;
import mypack.model.User;
import mypack.payload.BaseResponse;
import mypack.payload.ListWithPagingResponse;
import mypack.repository.NotificationRepository;
import mypack.repository.UserRepository;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notiRepo;

    @Autowired
    ModelMapper mapper;

    @Autowired
    UserRepository userRepo;

    public void addNotification(Long userId, String title, Post post) {
        Optional<User> oUser = userRepo.findById(userId);
        if (oUser.isEmpty()) {
            throw new CommonRuntimeException("No user is associated with this email !");
        }
        User user = oUser.get();
        Notification noti = new Notification();
        noti.setDate(new Date());
        noti.setUser(user);
        noti.setTitle(title);
        noti.setPost(post);
        notiRepo.save(noti);
    }

    public BaseResponse deleteNotification(Long userId, Long nitiId) {
        Optional<Notification> noti = notiRepo.findById(nitiId);
        if (noti.isEmpty())
            throw new CommonRuntimeException("Notification not found !");
        if (noti.get().getUser().getId() != userId)
            throw new CommonRuntimeException("Permission denied !");
        notiRepo.delete(noti.get());
        return new BaseResponse(true, "Success !");
    }

    public void addNotificationForListUser(List<Long> listUserId, String title, Post post) {
        List<Notification> lstNoti = new ArrayList<>();
        for (Long id : listUserId) {
            // If error when add 1 user then system will add others user notification
            try {
                Optional<User> oUser = userRepo.findById(id);
                Notification noti = new Notification();
                noti.setDate(new Date());
                noti.setUser(oUser.get());
                noti.setTitle(title);
                noti.setPost(post);
                lstNoti.add(noti);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        if (!lstNoti.isEmpty()) {
            notiRepo.saveAll(lstNoti);
        }

    }

    public ListWithPagingResponse<NotificationDTO> getListNotification(Long userId, Integer page, Integer limit) {
        User user = userRepo.findById(userId).orElseThrow(() -> new CommonRuntimeException("User not found !"));
        Pageable pageable = PageRequest.of(page - 1, limit);
        Long count = notiRepo.countByUser(user);

        Integer totalPage = (int) count.intValue() / limit + 1;
        List<NotificationDTO> data = notiRepo.findByUser(user, pageable).stream()
                .map(x -> mapper.map(x, NotificationDTO.class))
                .toList();
        return new ListWithPagingResponse<NotificationDTO>(page, totalPage, limit, data);
    }

    public BaseResponse deleteAllNotification(Long userId) {
        notiRepo.deleteALlUserNotice(userId);
        return new BaseResponse(true, "Success !");
    }
}
