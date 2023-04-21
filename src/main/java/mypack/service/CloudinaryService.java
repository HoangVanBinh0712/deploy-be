package mypack.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.Getter;
import lombok.Setter;
import mypack.controller.exception.CommonRuntimeException;

@Service
public class CloudinaryService {
	public static final String CLOUDINARY_CLOUD_NAME = "dh0hs3o2a";
	public static final String CLOUDINARY_API_KEY = "579738693487775";
	public static final String CLOUDINARY_API_SECRET = "xvyrXnuCvaVv1W09IoI1W77HzrE";

	private Cloudinary cloudinary;

	public CloudinaryService() {
		cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", CLOUDINARY_CLOUD_NAME, "api_key",
				CLOUDINARY_API_KEY, "api_secret", CLOUDINARY_API_SECRET, "secure", true));
	}

	public CloudinaryUploadResponse upload(byte[] data) throws IOException {
		Map response = cloudinary.uploader().upload(data, ObjectUtils.asMap("resource_type", "auto"));

		return new CloudinaryUploadResponse(response.get("secure_url").toString(), response.get("public_id").toString(),
				response.get("resource_type").toString());
	}

	public boolean delete(String publicId) throws IOException {
		Map response = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

		return response.get("result").toString().equals("ok");
	}

	@Getter
	@Setter
	public class CloudinaryUploadResponse {
		private String url;
		private String publicId;
		private String resourceType;

		public CloudinaryUploadResponse(String url, String publicId, String resourceType) {
			if (url.isEmpty()) {
				throw new CommonRuntimeException(
						"Upload file to cloudinary failed (no URL to access uploaded media found in Cloudinary response)");
			}
			if (publicId.isEmpty()) {
				throw new CommonRuntimeException(
						"Upload file to cloudinary failed (no public_id found in Cloudinary response)");
			}
			if (resourceType.isEmpty()) {
				throw new CommonRuntimeException(
						"Upload file to cloudinary failed (no resource_type found in Cloudinary response)");
			}

			this.url = url;
			this.publicId = publicId;
			this.resourceType = resourceType;
		}
	}
}
