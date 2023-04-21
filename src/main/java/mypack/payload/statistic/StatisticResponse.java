package mypack.payload.statistic;

import java.util.List;

import lombok.Data;

@Data
public class StatisticResponse {
    List<SumTotalByYearMonthCurrencyStatus> sumTotalByYearMonthCurrencyStatus;
}
