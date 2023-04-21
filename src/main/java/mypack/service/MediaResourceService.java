package mypack.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.model.MediaResource;
import mypack.repository.MediaResourceRepository;
import mypack.service.CloudinaryService.CloudinaryUploadResponse;

@Service
public class MediaResourceService{
	@Autowired
	private MediaResourceRepository repository;

	@Autowired
	CloudinaryService cloudinaryService;

	public MediaResource save(byte[] data) {
		try {
			CloudinaryUploadResponse resp = cloudinaryService.upload(data);
			MediaResource m = new MediaResource(resp.getUrl(), resp.getPublicId(), resp.getResourceType());
			return repository.save(m);
		} catch (IOException e) {
			throw new CommonRuntimeException(
					"IOException occurred when upload data to Cloudinary service (" + e.getMessage() + ")");
		}
	}

	public boolean delete(Long id) {
		Optional<MediaResource> m = repository.findById(id);
		if (!m.isPresent()) {
			throw new CommonRuntimeException("Media resource can not be found");
		}

		try {
			if (cloudinaryService.delete(m.get().getPublicId())) {
				repository.delete(m.get());
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			throw new CommonRuntimeException(
					"IOException occurred when delete data from Cloudinary service (" + e.getMessage() + ")");
		}
	}
}
