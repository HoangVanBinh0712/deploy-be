package mypack.repository.custom;

import java.util.List;

import mypack.model.User;
import mypack.utility.Page;
import mypack.utility.datatype.ERole;

public interface UserSerachCustomRepository {
    public Long countUser(String email, String name, String phone, ERole role, Boolean status, Long industryId,
            Long cityId);

    public List<User> getSearchUser(String email, String name, String phone, ERole role, Boolean status,
            Long industryId, Long cityId,
            Page page);
}
