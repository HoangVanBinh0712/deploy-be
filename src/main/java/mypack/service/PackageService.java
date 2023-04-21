package mypack.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.ServiceDTO;
import mypack.model.Service;
import mypack.model.User;
import mypack.payload.BaseResponse;
import mypack.payload.DataResponse;
import mypack.payload.ListResponse;
import mypack.payload.service.ServiceCreateRequest;
import mypack.payload.service.ServiceUpdateRequest;
import mypack.repository.ServiceRepository;
import mypack.repository.UserRepository;
import mypack.service.PackageService;
import mypack.utility.datatype.ERole;

@org.springframework.stereotype.Service
public class PackageService {

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	ModelMapper mapper;

	public DataResponse<ServiceDTO> create(ServiceCreateRequest request) {
		try {
			Service sv = mapper.map(request, Service.class);
			sv.setCreatedDate(new Date());
			sv = serviceRepository.save(sv);

			return new DataResponse<>(true, "Service create successfully.", mapper.map(sv, ServiceDTO.class));

		} catch (Exception ex) {
			return new DataResponse<>(false, "Something went wrong, please try again.", null);
		}
	}

	public DataResponse<ServiceDTO> update(ServiceUpdateRequest request) {
		Optional<Service> opt = serviceRepository.findById(request.getId());
		if (opt.isEmpty())
			return new DataResponse<>(false, "Service not found with id: " + request.getId(), null);
		Service sv = opt.get();
		if (!StringUtils.isBlank(request.getName()))
			sv.setName(request.getName());
		
		if (!StringUtils.isBlank(request.getDescription()))
			sv.setDescription(request.getDescription());
		
		if (request.getPrice() != null)
			sv.setPrice(request.getPrice());
		
		if (request.getCurrency() != null)
			sv.setCurrency(request.getCurrency());
		
		if (request.getActive() != null)
			sv.setActive(request.getActive());
		
		if (request.getCanSearchCV() != null)
			sv.setCanSearchCV(request.getCanSearchCV());
		
		if (request.getCanFilterCVSubmit() != null)
			sv.setCanFilterCVSubmit(request.getCanFilterCVSubmit());
		
		if (request.getType() != null)
			sv.setType(request.getType());
		
		if (request.getPostDuration() != null)
			sv.setPostDuration(request.getPostDuration());
		sv = serviceRepository.save(sv);

		return new DataResponse<>(true, "Update service successfully !", mapper.map(sv, ServiceDTO.class));

	}

	public ListResponse<ServiceDTO> getListService() {
		List<ServiceDTO> result = serviceRepository.findAll().stream().map(sv -> mapper.map(sv, ServiceDTO.class))
				.toList();
		if (result.isEmpty())
			return new ListResponse<>(false, "No service found.", result);
		return new ListResponse<>(result);
	}

	public ListResponse<ServiceDTO> getListService(Boolean active) {
		List<ServiceDTO> result = serviceRepository.findByActive(active).stream()
				.map(sv -> mapper.map(sv, ServiceDTO.class)).toList();
		if (result.isEmpty())
			return new ListResponse<>(false, "No service found.", result);
		return new ListResponse<>(result);
	}

	public BaseResponse deleteUserService(String empEmail) {
		Optional<User> optUser = userRepo.findByEmailAndRole(empEmail, ERole.ROLE_EMPLOYER);
		if (optUser.isEmpty())
			throw new CommonRuntimeException("User not found with Email: " + empEmail);
		User user = optUser.get();
		// Delete service of user
		user.setService(null);
		user.setServiceExpirationDate(null);
		userRepo.save(user);
		return new BaseResponse(true, "Success !");
	}
}
