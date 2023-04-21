package mypack.repository.custom;

import java.util.Date;
import java.util.List;

import mypack.model.Post;
import mypack.utility.Page;
import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EGender;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;
import mypack.utility.datatype.EStatus;

public interface PostSearchCustomRepository {
	public List<Post> postSearch(String keyword, Long recruit, Long salary, EMethod method, EPosition position,
			EExperience experience, EGender gender, ECurrency currency, Long authorId, Long industryId, Long cityId,
			EStatus status, Date expirationDate,
			Date startDate, Long serviceId, Page page);

	public Long postCountBeforeSearch(String keyword, Long recruit, Long salary, EMethod method, EPosition position,
			EExperience experience, EGender gender, ECurrency currency, Long authorId, Long industryId, Long cityId,
			EStatus status, Date expirationDate,
			Date startDate, Long serviceId);
}
