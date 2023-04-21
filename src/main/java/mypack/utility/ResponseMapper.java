package mypack.utility;

import java.util.List;

import org.modelmapper.ModelMapper;

import mypack.payload.ListWithPagingResponse;

public class ResponseMapper {
	public static <T, R> ListWithPagingResponse<T> listWithPagingResponseMakeup(List<R> data, Page page,
			Class<T> dtoType, ModelMapper modelMapper) {
		return new ListWithPagingResponse<>(page.getPageNumber() + 1, page.getTotalPage() + 1, page.getPageSize(),
				data.stream().map(p -> modelMapper.map(p, dtoType)).toList());
	}
}
