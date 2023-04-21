//package mypack.service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import mypack.controller.exception.CommonRuntimeException;
//import mypack.dto.CommentDTO;
//import mypack.dto.UserDTO;
//import mypack.model.Comment;
//import mypack.model.Post;
//import mypack.model.User;
//import mypack.payload.BaseResponse;
//import mypack.payload.CommentResponse;
//import mypack.repository.CommentRepository;
//import mypack.repository.PostRepository;
//import mypack.repository.UserRepository;
//import mypack.utility.datatype.EStatus;
//
//@Service
//public class CommentService {
//
//	@Autowired
//	CommentRepository commentRepository;
//
//	@Autowired
//	UserRepository userRepo;
//
//	@Autowired
//	PostRepository postRepository;
//
//	@Autowired
//	ModelMapper modelMapper;
//
//	public BaseResponse comment(String jobseekerEmail, Long postId, Long replyId, String content) {
//
//		// Only one level of parent
//		Optional<User> user = userRepo.findByEmail(jobseekerEmail);
//		if (user.isEmpty())
//			throw new CommonRuntimeException("User not found with email: " + jobseekerEmail);
//		Optional<Post> post = postRepository.findById(postId);
//		if (post.isEmpty() || post.get().getStatus() != EStatus.ACTIVE)
//			throw new CommonRuntimeException("Post not found with Id: " + postId);
//		if (replyId != null) {
//			Optional<Comment> parent = commentRepository.findById(replyId);
//			if (parent.isEmpty())
//				throw new CommonRuntimeException("Comment not found !");
//			Comment pr = parent.get();
//			// Find the biggest parent
//			while (pr.getReply() != null) {
//				pr = pr.getReply();
//			}
//			Comment cm = new Comment(null, user.get(), post.get(), content, new Date(), pr, null);
//			commentRepository.save(cm);
//		} else {
//			Comment cm = new Comment(null, user.get(), post.get(), content, new Date(), null, null);
//			commentRepository.save(cm);
//		}
//
//		// Send notification
//		return new BaseResponse(true, "Success");
//	}
//
//	public List<CommentResponse> getPostComment(Long postId) {
//		Optional<Post> post = postRepository.findById(postId);
//		if (post.isEmpty() || post.get().getStatus() != EStatus.ACTIVE)
//			throw new CommonRuntimeException("Post not found with Id: " + postId);
//
//		// Find only parent comment
//		List<Comment> lstComment = commentRepository.findCommentByPost(post.get().getId());
//		List<CommentResponse> res = new ArrayList<>();
//		if (lstComment.isEmpty())
//			throw new CommonRuntimeException("No comment found for this post !");
//		// for each parent comment get list childs
//		for (Comment c : lstComment) {
//			List<CommentDTO> childs = c.getChilds().stream().map(x -> modelMapper.map(x, CommentDTO.class)).toList();
//			res.add(new CommentResponse(c.getId(), c.getContent(), modelMapper.map(c.getUser(), UserDTO.class),
//					childs));
//
//		}
//		if (res.isEmpty())
//			throw new CommonRuntimeException("No comment found for this post !");
//
//		return res;
//	}
//
//}
