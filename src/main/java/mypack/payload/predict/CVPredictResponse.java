package mypack.payload.predict;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import mypack.payload.BaseResponse;

@Getter
@Setter
public class CVPredictResponse<T> extends BaseResponse {
	private int currentPage;
	private int totalPage;
	private List<T> data;
	private Map<String, String> predictResult;
	private String currentView;
	public CVPredictResponse(int currentPage, int totalPage, List<T> data, Map<String, String> predictResult,
			String currentView) {
		super(true, "");
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.data = data;
		this.predictResult = predictResult;
		this.currentView = currentView;
	}
}