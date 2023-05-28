package mypack.payload.admin;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mypack.dto.OrderDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatistic {
    private List<OrderDTO> orders;
    private Long total;
}
