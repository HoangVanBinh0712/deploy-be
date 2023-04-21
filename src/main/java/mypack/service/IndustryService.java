package mypack.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.IndustryDTO;
import mypack.model.Industry;
import mypack.payload.BaseResponse;
import mypack.payload.DataResponse;
import mypack.payload.ListResponse;
import mypack.repository.IndustryRepository;

@Service
public class IndustryService {
	@Autowired
	IndustryRepository industryRepo;

	@Autowired
	ModelMapper modelMapper;

	public DataResponse<IndustryDTO> create(IndustryDTO request) {

		Industry industry = modelMapper.map(request, Industry.class);
		return new DataResponse<>(true, "Add industry successfully",
				modelMapper.map(industryRepo.save(industry), IndustryDTO.class));
	}

	public DataResponse<IndustryDTO> update(IndustryDTO request) {
		if (request.getId() == null)
			throw new CommonRuntimeException("Industry not found !");

		Optional<Industry> optField = industryRepo.findById(request.getId());
		if (optField.isEmpty())
			throw new CommonRuntimeException("Industry not found !");

		Industry industry = optField.get();

		industry.setName(request.getName());
		
		return new DataResponse<>(true, "", modelMapper.map(industryRepo.save(industry), IndustryDTO.class));
	}

	public ListResponse<IndustryDTO> getAll() {
		List<IndustryDTO> industries = industryRepo.findAll().stream().map(x -> modelMapper.map(x, IndustryDTO.class))
				.toList();
		return new ListResponse<>(industries);
	}

	@Transactional
	public BaseResponse delete(Long id) {
		try {
			industryRepo.deleteById(id);
			return new BaseResponse(true, "Delete successfully !");
		
		}catch(Exception ex) {
			return new BaseResponse(false, "Cannot delete industry !");
		}
	}

}
