package mypack.repository.custom;

import java.util.List;

import mypack.dto.ProfileDTO;
import mypack.model.Profile;
import mypack.payload.ListWithPagingResponse;
import mypack.utility.Page;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;

public interface ProfileSearchCustomRepository {
    public ListWithPagingResponse<ProfileDTO> profileSearch(String keyword, EMethod method, EPosition position,
            EExperience experience, Long industryId, Long cityId, Page page);

    public Long profileCountBeforeSearch(String keyword, EMethod method, EPosition position,
            EExperience experience, Long industryId, Long cityId);
}
