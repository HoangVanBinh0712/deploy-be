package mypack.service;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.PostDTO;
import mypack.model.Industry;
import mypack.model.Profile;
import mypack.model.pk.ProfilePK;
import mypack.payload.ListWithPagingResponse;
import mypack.payload.predict.CVPredictResponse;
import mypack.repository.IndustryRepository;
import mypack.repository.PostRepository;
import mypack.repository.ProfileRepository;
import mypack.utility.Page;
import mypack.utility.datatype.EStatus;

@Service
public class CVPredictService {
	@Value("${career.app.ai.url}")
	String aiUurl;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	IndustryRepository IndustryRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PostRepository postRepository;

	public CVPredictResponse<PostDTO> predict(Long mediaId, Long userId) {
		Profile profile = profileRepository.getReferenceById(new ProfilePK(userId, mediaId));

		try {
			String resume = profile.getWorkExperiences();
			String skill = profile.getSkillsAndKnowledges();

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON );

			JSONObject requestBody = new JSONObject();
			requestBody.put("resume", resume);
			requestBody.put("skill", skill);
			
			HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);
			String response = restTemplate.postForEntity(aiUurl, entity, String.class).getBody();
			JSONObject jObject = new JSONObject(response); // json
			JSONArray array = jObject.getJSONArray("results"); // get data object
			Map<String, String> dict = new HashMap<>();
			String highestIndustry = null;
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = (JSONObject) array.get(i);
				String key = obj.keys().next().toString();
				dict.put(key, obj.get(key).toString());
				if (highestIndustry == null)
					highestIndustry = key;
			}

			Industry ind = IndustryRepository.getByNameLike(highestIndustry);

			int count = postRepository.postCountBeforeSearch(null, null, null, null, null, null, null, null, null,
					ind.getId(), null, EStatus.ACTIVE, null, null, null).intValue();

			Page page = new Page(null, null, count);

			List<PostDTO> posts = postRepository.postSearch(null, null, null, null, null, null, null, null, null,
					ind.getId(), null, EStatus.ACTIVE, null, null, null, page).stream()
					.map(p -> modelMapper.map(p, PostDTO.class)).toList();

			return new CVPredictResponse<>(page.getPageNumber() + 1, page.getTotalPage(), posts, dict, highestIndustry);
		} catch (Exception e) {
			throw new CommonRuntimeException("Error when predicting your resume. Upload a new resume and try again !");
		}

	}

	public ListWithPagingResponse<PostDTO> getPostByIndustry(Long industryId, Integer pageNumber, Integer limit) {
		Optional<Industry> optIndustry = IndustryRepository.findById(industryId);
		if (optIndustry.isEmpty())
			throw new CommonRuntimeException("Industry not found with code : " + industryId);
		Industry Industry = optIndustry.get();
		/*
		 * 
		 * String keyword, Long recruit, Long salary, EMethod method, EPosition
		 * position, EExperience experience, EGender gender, ECurrency currency, Long
		 * authorId, Long industryId, Long cityId, EStatus status, Date expirationDate,
		 * Date startDate, Long serviceId
		 * 
		 */
		int count = postRepository.postCountBeforeSearch(null, null, null, null, null, null, null, null, null,
				Industry.getId(), null, null, null, null, null).intValue();

		Page page = new Page(pageNumber, limit, count);

		List<PostDTO> posts = postRepository.postSearch(null, null, null, null, null, null, null, null, null,
				Industry.getId(), null, null, null, null, null, page).stream()
				.map(p -> modelMapper.map(p, PostDTO.class)).toList();

		return new ListWithPagingResponse<>(page.getPageNumber() + 1, page.getTotalPage(), page.getPageSize(), posts);
	}

	public String cleanResume(String resume) {
		resume = resume.replaceAll("http\\S+\\s*", "");

		resume = resume.replaceAll("\\s+", " ");
		resume = resume.replaceAll("\"[^\\\\x00-\\\\x7f]\"", " ");
		resume = resume.replaceAll("@\\S+", " ");
		resume = resume.replaceAll("#\\S+", " ");
		resume = resume.replaceAll("RT|cc", " ");

		return resume;
	}
}