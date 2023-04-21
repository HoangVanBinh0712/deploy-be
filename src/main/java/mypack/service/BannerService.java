package mypack.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.BannerDTO;
import mypack.model.Banner;
import mypack.model.MediaResource;
import mypack.payload.BaseResponse;
import mypack.repository.BannerRepository;

@Service

public class BannerService {
	@Autowired
	private BannerRepository bannerRepository;

	@Autowired
	private MediaResourceService mediaResourceService;
	@Autowired
	private ModelMapper mapper;

	public BaseResponse addBanner(MultipartFile file) {

		try {
			MediaResource img = mediaResourceService.save(file.getBytes());
			Banner banner = new Banner();
			banner.setImage(img);
			bannerRepository.save(banner);
		} catch (IOException e) {
			throw new CommonRuntimeException("Error occur when uploading new image !");
		}
		return new BaseResponse(true, "Success");
	}

	@Transactional
	public BaseResponse updateBanner(Long id, MultipartFile file) {
		Banner banner = bannerRepository.findById(id)
				.orElseThrow(() -> new CommonRuntimeException("Banner not found !"));
		try {
			if (!mediaResourceService.delete(banner.getImage().getId()))
				throw new CommonRuntimeException("Error occur when deleting old image !");
			MediaResource img = mediaResourceService.save(file.getBytes());
			banner.setImage(img);
			bannerRepository.save(banner);
		} catch (IOException e) {
			throw new CommonRuntimeException("Error occur when uploading new image !");
		}
		return new BaseResponse(true, "Success");
	}

	public BaseResponse deleteBanner(Long id) {
		Banner banner = bannerRepository.findById(id)
				.orElseThrow(() -> new CommonRuntimeException("Banner not found !"));
		Long imageID = banner.getImage().getId();
		banner.setImage(null);
		bannerRepository.save(banner);
		mediaResourceService.delete(imageID);
		bannerRepository.delete(banner);

		return new BaseResponse(true, "Success");
	}

	public List<BannerDTO> getBanners() {
		return bannerRepository.findAll().stream().map(x -> mapper.map(x, BannerDTO.class)).toList();
	}
}
