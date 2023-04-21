package mypack.payload.statistic;

import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EROrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SumTotalByYearMonthCurrencyStatus {

    private Integer month;
    private Long service;
    private EROrderStatus status;
    private ECurrency currency;
    private Double total;

}
