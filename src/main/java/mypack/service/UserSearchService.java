package mypack.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.dto.UserDTO;
import mypack.payload.ListWithPagingResponse;
import mypack.repository.UserRepository;
import mypack.utility.Page;
import mypack.utility.datatype.ERole;

@Service
public class UserSearchService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public Long getCountBeforSearch(String email, String name, String phone, ERole role, Boolean status,
            Long industryId, Long cityId) {
        return userRepository.countUser(email, name, phone, role, status, industryId, cityId);
    }

    public ListWithPagingResponse<UserDTO> search(String email, String name, String phone, ERole role, Boolean status,
            Long industryId, Long cityId, Page page) {

        return new ListWithPagingResponse<>(page.getPageNumber() + 1, page.getTotalPage(), page.getPageSize(),
                userRepository.getSearchUser(email, name, phone, role, status, industryId, cityId, page)
                        .stream().map(p -> modelMapper.map(p, UserDTO.class)).toList());
    }
}
