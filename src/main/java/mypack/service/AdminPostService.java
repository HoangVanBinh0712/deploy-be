package mypack.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.PostDTO;
import mypack.model.Post;
import mypack.model.User;
import mypack.payload.BaseResponse;
import mypack.payload.DataResponse;
import mypack.repository.FollowUserRepository;
import mypack.repository.PostRepository;
import mypack.repository.UserRepository;
import mypack.utility.datatype.ERole;
import mypack.utility.datatype.EStatus;

@Service
public class AdminPostService {
	@Autowired
	PostRepository postRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	NotificationService notificationService;

	@Autowired
	UserRepository userRepo;

	@Value("${email.content.employer-notification-post-accept}")
	String contentAccept;

	@Value("${email.content.employer-notification-post-unaccept}")
	String contentUnaccept;

	@Autowired
	SendEmailService sendEmailService;

	@Autowired
	FollowUserRepository followUserRepository;

	@Value("${email.content.user-notification}")
	private String content;

	public BaseResponse acceptPost(String email, Long postId) {
		Optional<Post> optPost = postRepository.findById(postId);
		if (optPost.isEmpty())
			throw new CommonRuntimeException("Post not found with id: " + postId);
		Post post = optPost.get();
		User admin = userRepo.findByEmailAndRole(email, ERole.ROLE_ADMIN).get();
		post.setAcceptedBy(admin);
		post.setAcceptedDate(new Date());
		post.setStatus(EStatus.ACTIVE);

		postRepository.save(post);
		// Add message here for employer
		notificationService.addNotification(post.getAuthor().getId(),
				"Admin has accepted your post - " + post.getTitle() + " !", post);
		if (post.getAuthor().getEmailConfirm())
			sendEmailService.sendMailForNotification(new String[] { post.getAuthor().getEmail() },
					String.format(contentAccept, post.getTitle()));
		// Send notification for list user followed
		List<Long> listUserId = followUserRepository.getIdFollowers(post.getAuthor().getId());
		notificationService.addNotificationForListUser(listUserId,
				"Your followed company has posted a new job - " + post.getTitle() + " !",
				post);
		// send email
		String[] listUserEmail = userRepo.getListEmailUser(listUserId);
		if (listUserEmail.length > 0)
			sendEmailService.sendMailForNotification(listUserEmail, String.format(content, post.getTitle()));
		return new BaseResponse(true, "Accept susscessfully post with id: " + postId);
	}

	public BaseResponse unAcceptPost(String email, Long postId) {
		Optional<Post> optPost = postRepository.findById(postId);
		if (optPost.isEmpty())
			throw new CommonRuntimeException("Post not found with id: " + postId);
		Post post = optPost.get();
		User admin = userRepo.findByEmailAndRole(email, ERole.ROLE_ADMIN).get();

		post.setStatus(EStatus.DELETED_BY_ADMIN);
		post.setAcceptedBy(admin);
		post.setAcceptedDate(new Date());
		postRepository.save(post);

		// Add message here for employer
		notificationService.addNotification(post.getAuthor().getId(),
				"Admin has Delete your post - " + post.getTitle() + " !", post);
		if (post.getAuthor().getEmailConfirm())
			sendEmailService.sendMailForNotification(new String[] { post.getAuthor().getEmail() },
					String.format(contentUnaccept, post.getTitle()));

		return new BaseResponse(true, "Unaccept susscessfully post with id: " + postId);
	}

	public DataResponse<PostDTO> getPostDetail(Long postId) {
		Optional<Post> optPost = postRepository.findById(postId);
		if (optPost.isEmpty())
			throw new CommonRuntimeException("Post not found with id: " + postId);
		return new DataResponse<>(true, "", modelMapper.map(optPost.get(), PostDTO.class));
	}

}
