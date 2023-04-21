package mypack.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.PostDTO;
import mypack.model.Follow;
import mypack.model.Post;
import mypack.model.User;
import mypack.model.pk.FollowPK;
import mypack.payload.BaseResponse;
import mypack.payload.ListResponse;
import mypack.repository.FollowRepository;
import mypack.repository.PostRepository;
import mypack.repository.UserRepository;
import mypack.utility.datatype.ERole;
import mypack.utility.datatype.EStatus;

@Service
public class FollowService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	FollowRepository followRepo;

	@Autowired
	PostRepository postRepository;

	@Autowired
	ModelMapper modelMapper;

	public BaseResponse followPost(String jobseekerEmail, Long postId, Boolean follow) {

		if (follow) {
			// Follow
			Optional<User> user = userRepo.findByEmailAndRole(jobseekerEmail, ERole.ROLE_USER);
			if (user.isEmpty())
				throw new CommonRuntimeException("User not found with email: " + jobseekerEmail);
			Optional<Post> post = postRepository.findById(postId);
			if (post.isEmpty() || post.get().getStatus() != EStatus.ACTIVE)
				throw new CommonRuntimeException("Post not found with Id: " + postId);

			Follow fl = new Follow(new FollowPK(user.get().getId(), post.get().getId()), post.get(), user.get(),
					new Date());

			followRepo.save(fl);

			return new BaseResponse(true, "Follow post successfully !");
		} else {
			// unfollow
			Optional<User> user = userRepo.findByEmailAndRole(jobseekerEmail, ERole.ROLE_USER);
			if (user.isEmpty())
				throw new CommonRuntimeException("User not found with email: " + jobseekerEmail);
			Optional<Post> post = postRepository.findById(postId);
			if (post.isEmpty() || post.get().getStatus() != EStatus.ACTIVE)
				throw new CommonRuntimeException("Post not found with Id: " + postId);

			FollowPK pk = new FollowPK(user.get().getId(), post.get().getId());

			followRepo.deleteById(pk);

			return new BaseResponse(true, "UnFollow post successfully !");
		}
	}

	public ListResponse<PostDTO> getListPostFollowed(String jobseekerEmail) {
		Optional<User> user = userRepo.findByEmailAndRole(jobseekerEmail, ERole.ROLE_USER);
		if (user.isEmpty())
			throw new CommonRuntimeException("User not found with email: " + jobseekerEmail);
		List<Long> lstPost = followRepo.getListPostIdFollow(user.get().getId());

		if (lstPost.isEmpty())
			throw new CommonRuntimeException("Your did not follow to any post !");
		List<PostDTO> posts = postRepository.findAllById(lstPost).stream().map(p -> modelMapper.map(p, PostDTO.class))
				.toList();
		if (posts.isEmpty())
			throw new CommonRuntimeException("Your did not follow to any post or post is not available to view !");
		return new ListResponse<>(posts);
	}
}
