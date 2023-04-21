package mypack.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mypack.dto.AchievementDTO;
import mypack.dto.BannerDTO;
import mypack.dto.CVSubmitDTO;
import mypack.dto.ChatMessageDTO;
import mypack.dto.IndustryDTO;
import mypack.dto.ProfileDTO;
import mypack.dto.UserDTO;
import mypack.model.Achievement;
import mypack.model.Banner;
import mypack.model.CVSubmit;
import mypack.model.ChatMessage;
import mypack.model.Industry;
import mypack.model.Profile;
import mypack.model.User;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMaper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.createTypeMap(Industry.class, IndustryDTO.class).addMappings(mapper -> {

		});
		modelMapper.createTypeMap(User.class, UserDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getAvatar().getUrl(), (dst, value) -> dst.setUrlAvatar((String) value));
			mapper.map(src -> src.getCover().getUrl(), (dst, value) -> dst.setUrlCover((String) value));

		});
		modelMapper.createTypeMap(Profile.class, ProfileDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getMediaResource().getUrl(), (dst, value) -> dst.setUrl((String) value));
			mapper.map(src -> src.getMediaResource().getId(), (dst, value) -> dst.setMediaId((Long) value));

		});

		modelMapper.createTypeMap(CVSubmit.class, CVSubmitDTO.class).addMappings(mapper -> mapper
				.map(src -> src.getProfile().getMediaResource().getUrl(), (dst, value) -> dst.setUrl((String) value)));
		modelMapper.createTypeMap(Achievement.class, AchievementDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getImage().getUrl(), (dst, value) -> dst.setImageUrl((String) value));
		});
		modelMapper.createTypeMap(Banner.class, BannerDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getImage().getUrl(), (dst, value) -> dst.setUrl((String) value));
		});
		modelMapper.createTypeMap(ChatMessage.class, ChatMessageDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getChatRoom().getId(), (dst, value) -> dst.setChatRoomId((Long) value));
		});
		return modelMapper;
	}
}
