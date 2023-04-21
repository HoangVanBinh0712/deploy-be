package mypack.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.CityDTO;
import mypack.model.City;
import mypack.payload.BaseResponse;
import mypack.payload.DataResponse;
import mypack.payload.ListResponse;
import mypack.repository.CityRepository;	

@Service
public class CityService {
	@Autowired
	CityRepository cityRepo;

	@Autowired
	ModelMapper modelMapper;

	public DataResponse<CityDTO> create(CityDTO request) {

		City city = modelMapper.map(request, City.class);

		return new DataResponse<>(true, "Add City successfully", modelMapper.map(cityRepo.save(city), CityDTO.class));
	}

	public DataResponse<CityDTO> update(CityDTO request) {
		if (request.getId() == null)
			throw new CommonRuntimeException("City not found !");

		Optional<City> optField = cityRepo.findById(request.getId());
		if (optField.isEmpty())
			throw new CommonRuntimeException("City not found !");

		City city = optField.get();

		city.setName(request.getName());

		return new DataResponse<>(true, "", modelMapper.map(cityRepo.save(city), CityDTO.class));
	}

	public ListResponse<CityDTO> getAll() {
		List<CityDTO> cities = cityRepo.findAll().stream().map(x -> modelMapper.map(x, CityDTO.class)).toList();
		return new ListResponse<>(cities);
	}

	public BaseResponse delete(Long id) {
		cityRepo.deleteById(id);
		return new BaseResponse(true, "Delete successfully !");
	}

}
