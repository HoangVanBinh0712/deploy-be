package mypack.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.UserDTO;
import mypack.model.User;
import mypack.repository.UserRepository;
import mypack.utility.datatype.ERole;

@Service
public class AdminService {

	@Autowired
	UserRepository adminRepo;

	@Autowired
	ModelMapper modelMapper;

	public UserDTO getAdminProfile(String email) {
		Optional<User> optAdmin = adminRepo.findByEmailAndRole(email, ERole.ROLE_ADMIN);
		if (optAdmin.isEmpty())
			throw new CommonRuntimeException("User not found with Admin: " + email);
		return modelMapper.map(optAdmin.get(), UserDTO.class);
	}

}
