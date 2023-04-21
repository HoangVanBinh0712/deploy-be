package mypack.payload.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticForReport {
	private Integer month;
	private Boolean handle;
	private Long count;
}
