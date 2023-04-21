package mypack.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.PostDTO;
import mypack.model.City;
import mypack.model.Industry;
import mypack.model.Post;
import mypack.model.User;
import mypack.model.ViewPost;
import mypack.payload.BaseResponse;
import mypack.payload.DataResponse;
import mypack.payload.post.PostRequest;
import mypack.repository.CityRepository;
import mypack.repository.IndustryRepository;
import mypack.repository.OrderRepository;
import mypack.repository.PostRepository;
import mypack.repository.ServiceRepository;
import mypack.repository.UserRepository;
import mypack.repository.ViewPostRepository;
import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EStatus;

@Service
public class EmployerPostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    IndustryRepository industryRepository;

    @Autowired
    ServiceRepository serviceRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    OrderRepository postOrderRepository;

    @Autowired
    MediaResourceService mediaResourceService;
    @Autowired
    ViewPostRepository viewPostRepository;
    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public DataResponse<PostDTO> create(Long userId, PostRequest request) {
        if (request.getCurrency() != ECurrency.AGREEMENT && request.getSalary() == null)
            throw new CommonRuntimeException("Salary must not be null !");

        Date today = new Date();

        Post post = modelMapper.map(request, Post.class);

        Optional<City> city = cityRepository.findById(request.getCity());
        if (city.isEmpty())
            throw new CommonRuntimeException("City not found with Id: " + request.getCity());

        Optional<Industry> industry = industryRepository.findById(request.getIndustry());

        if (industry.isEmpty())
            throw new CommonRuntimeException("Industry not found with Id: " + request.getIndustry());
        User user = userRepo.findById(userId).get();

        if (!user.getEmailConfirm())
            throw new CommonRuntimeException("Please confirm your email address before uploading post !");

        if (user.getService() == null
                || user.getServiceExpirationDate() != null && user.getServiceExpirationDate().before(today))
            throw new CommonRuntimeException(
                    "Your service package has expirated or You do not eligible to use our service !. Please check again your service !");

        if (request.getCurrency().equals(ECurrency.AGREEMENT))
            post.setSalary(null);
        post.setCreateDate(today);
        post.setViewCount(0);
        post.setCity(city.get());
        post.setIndustry(industry.get());
        post.setService(user.getService());
        post.setAuthor(user);
        post.setStatus(EStatus.WAIT_FOR_ACCEPT);

        Integer duration = user.getService().getPostDuration().intValue();
        // Format and caculate date
        SimpleDateFormat cmf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate maxDate = LocalDate.parse(cmf.format(today)).plusMonths(duration);
        LocalDate expirationDate = LocalDate.parse(cmf.format(request.getExpirationDate()));
        String message = "Add new post successfully !";
        if (request.getExpirationDate().before(today))
            throw new CommonRuntimeException("Expiration day must after today !");
        if (expirationDate.isAfter(maxDate)) {
            message = "Your post max expiration date is " + duration.toString()
                    + " month(s), so we have set the day due to this policy !";
            expirationDate = maxDate;
        }

        post.setExpirationDate(Date.from(expirationDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        post = postRepository.save(post);

        return new DataResponse<>(true, message,
                modelMapper.map(post, PostDTO.class));
    }

    @Transactional
    public DataResponse<PostDTO> update(Long userId, Long postId, PostRequest request) {
        if (request.getCurrency() != ECurrency.AGREEMENT && request.getSalary() == null)
            throw new CommonRuntimeException("Salary must not be null !");
        User user = userRepo.findById(userId).get();
        Date today = new Date();

        if (user.getService() == null
                || user.getServiceExpirationDate() != null && user.getServiceExpirationDate().before(today))
            throw new CommonRuntimeException(
                    "Your service package has expirated or You do not eligible to use our service !. Please check again your service !");

        if (!user.getEmailConfirm())
            throw new CommonRuntimeException("Please confirm your email address before adding post !");

        Optional<Post> opt = postRepository.findById(postId);
        if (!opt.isPresent())
            throw new CommonRuntimeException("Post not found with Id: " + postId);
        Post post = opt.get();

        if (!post.getAuthor().getId().equals(userId))
            throw new CommonRuntimeException("Unable to delete other employer's post.");

        Optional<City> city = cityRepository.findById(request.getCity());
        if (city.isEmpty())
            throw new CommonRuntimeException("City not found with Id: " + request.getCity());

        Optional<Industry> industry = industryRepository.findById(request.getIndustry());

        if (industry.isEmpty())
            throw new CommonRuntimeException("Industry not found with Id: " + request.getIndustry());
        post.setCity(city.get());
        post.setIndustry(industry.get());
        post.setCreateDate(today);
        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setMethod(request.getMethod());
        post.setPosition(request.getPosition());
        post.setExperience(request.getExperience());
        post.setGender(request.getGender());
        post.setRequirement(request.getRequirement());
        post.setContact(request.getContact());
        post.setSalary(request.getSalary());
        post.setCurrency(request.getCurrency());
        post.setLocation(request.getLocation());
        post.setRecruit(request.getRecruit());
        post.setService(user.getService());
        Integer duration = user.getService().getPostDuration().intValue();

        SimpleDateFormat cmf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate maxDate = LocalDate.parse(cmf.format(today)).plusMonths(duration);
        LocalDate expirationDate = LocalDate.parse(cmf.format(request.getExpirationDate()));
        String message = "Update post successfully !";

        if (request.getExpirationDate().before(today))
            throw new CommonRuntimeException("Expiration day must after today !");

        if (expirationDate.isAfter(maxDate)) {
            message = "Your post max expiration date is " + duration.toString()
                    + " month(s), so we have set the day due to this policy !";
            expirationDate = maxDate;
        }

        post.setExpirationDate(Date.from(expirationDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        post = postRepository.save(post);

        return new DataResponse<>(true, message,
                modelMapper.map(post, PostDTO.class));
    }

    public BaseResponse deletePost(Long empId, Long postId) {
        Optional<Post> optPost = postRepository.findById(postId);

        if (optPost.isEmpty())
            throw new CommonRuntimeException("Post not found with id: " + postId);

        Post post = optPost.get();
        if (!post.getAuthor().getId().equals(empId))
            throw new CommonRuntimeException("Unable to delete other employer's post.");

        if (post.getStatus() != EStatus.DELETED_BY_ADMIN) {
            post.setStatus(EStatus.DELETED);
            postRepository.save(post);
            return new BaseResponse(true, "Delete successfully post with id: " + postId);

        } else {
            return new BaseResponse(false, "Post is already deleted by admin !");

        }

    }

    public BaseResponse changePostStatus(Long empId, Long postId, EStatus status) {

        // Delete -> Delete
        // Recover -> Wait for accept
        // Active , disable
        if (status == EStatus.DELETED_BY_ADMIN)
            throw new CommonRuntimeException("Only admin can do that !");

        Optional<Post> optPost = postRepository.findById(postId);

        if (optPost.isEmpty())
            throw new CommonRuntimeException("Post not found with id: " + postId);

        Post post = optPost.get();
        if (!post.getAuthor().getId().equals(empId))
            throw new CommonRuntimeException("Unable to update other employer's post.");

        if (post.getStatus() != EStatus.DELETED_BY_ADMIN) {

            if (post.getStatus() == EStatus.WAIT_FOR_ACCEPT)
                if (status == EStatus.DELETED) {
                    post.setStatus(status);
                    postRepository.save(post);
                    return new BaseResponse(true, "Update post successfully !");
                } else {
                    throw new CommonRuntimeException(
                            "Post has not been accepted yet ! You can only delete this post !");

                }
            // Active, disable, delete
            // Recover deleted post

            if (post.getStatus() == EStatus.DELETED && status != EStatus.DELETED) {
                post.setStatus(EStatus.WAIT_FOR_ACCEPT);
                post.setCreateDate(new Date());
                postRepository.save(post);
                return new BaseResponse(true, "Update post successfully !");

            }
            post.setStatus(status);
            postRepository.save(post);
            return new BaseResponse(true, "Update post successfully !");

        } else {
            return new BaseResponse(false, "Post is already deleted by admin !");

        }

    }

    // Jobseeker get post Details
    public DataResponse<PostDTO> getDetail(Long postId) {
        Optional<Post> optPost = postRepository.findById(postId);
        if (optPost.isEmpty())
            throw new CommonRuntimeException("Post not found with id: " + postId);
        // Update viewCount
        Post post = optPost.get();
        if (post.getStatus() != EStatus.ACTIVE && post.getStatus() != EStatus.DISABLE ||
                post.getExpirationDate().before(new Date())) {
            throw new CommonRuntimeException("Post not available to view !");
        }
        PostDTO res = modelMapper.map(post, PostDTO.class);
        ViewPost vp = new ViewPost();
        vp.setDate(new Date());
        vp.setPost(post);
        if (post.getViewCount() == null) {
            post.setViewCount(1);
        } else {
            post.setViewCount(post.getViewCount() + 1);
        }
        //save vp and post
        viewPostRepository.save(vp);
        postRepository.save(post);
        
   
        return new DataResponse<>(true, "", res);
    }

    public DataResponse<PostDTO> getEmployerPostDetail(Long empId, Long postId) {
        Optional<Post> optPost = postRepository.findById(postId);
        if (optPost.isEmpty())
            throw new CommonRuntimeException("Post not found with id: " + postId);
        Post post = optPost.get();
        if (post.getAuthor().getId() != empId)
            throw new CommonRuntimeException("Post not found with id: " + postId);

        return new DataResponse<>(true, "", modelMapper.map(post, PostDTO.class));
    }
}
