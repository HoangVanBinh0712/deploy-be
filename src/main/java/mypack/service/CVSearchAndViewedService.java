package mypack.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.ProfileDTO;
import mypack.dto.UserDTO;
import mypack.model.CVViewed;
import mypack.model.Profile;
import mypack.model.User;
import mypack.model.pk.CVViewedPK;
import mypack.model.pk.ProfilePK;
import mypack.payload.BaseResponse;
import mypack.payload.ListWithPagingResponse;
import mypack.repository.CVViewedRepository;
import mypack.repository.ProfileRepository;
import mypack.repository.UserRepository;
import mypack.utility.ModelSorting;
import mypack.utility.Page;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;
import mypack.utility.datatype.ERole;

@Service
public class CVSearchAndViewedService {

	@Autowired
	ProfileRepository profileRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	CVViewedRepository cvViewedRepository;

	@Autowired
	ModelMapper mapper;

	public Boolean isEligible(Long empId) {
		User us = userRepo.findById(empId).orElseThrow(() -> new CommonRuntimeException("Employer not found !"));
		if (us.getService() != null && us.getService().getCanSearchCV() != null
				&& us.getService().getCanSearchCV() == true && us.getServiceExpirationDate().after(new Date()))
			return true;
		return false;
	}

	public ListWithPagingResponse<ProfileDTO> searchProfile(String keyword, EMethod method, EPosition position,
			EExperience experience, Long industryId, Long cityId, Integer pageNumber, Integer limit, Integer sortBy,
			Boolean sortDescending) {
		Long c = profileRepo.profileCountBeforeSearch(keyword, method, position, experience, industryId, cityId);

		Page page = new Page(pageNumber, limit, c.intValue(), ModelSorting.getProfileSort(sortBy, sortDescending));
		return profileRepo.profileSearch(keyword, method, position, experience, industryId, cityId, page);
	}

	public BaseResponse addViewed(String empEmail, Long mediaId, Long userId) {
		Optional<User> emp = userRepo.findByEmailAndRole(empEmail, ERole.ROLE_EMPLOYER);
		if (emp.isEmpty())
			throw new CommonRuntimeException("User not found with email: " + empEmail);
		Optional<Profile> pro = profileRepo.findById(new ProfilePK(userId, mediaId));
		if (pro.isEmpty())
			throw new CommonRuntimeException("Profile not found !");
		CVViewedPK pk = new CVViewedPK(emp.get().getId(), userId, mediaId);
		CVViewed cv = new CVViewed(pk, emp.get(), pro.get(), new Date());
		cvViewedRepository.save(cv);

		return new BaseResponse(true, "Success");

	}

	public Long getCountViewForCV(Long userId, Long mediaId) {
		return cvViewedRepository.countViewedForCV(mediaId, userId);
	}

	public List<UserDTO> getEmployerViewedCV(Long userId, Long mediaId) {
		List<CVViewed> lst = cvViewedRepository.getEmployerViewedCV(mediaId, userId);
		if (lst.isEmpty())
			throw new CommonRuntimeException("No employer has viewed your cv yet !");
		return lst.stream().map(x -> x.getViewer()).map(x -> mapper.map(x, UserDTO.class)).toList();

	}
}
