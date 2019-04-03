package yaremko.yaromyr.dto.response;

import lombok.Getter;
import lombok.Setter;
import yaremko.yaromyr.dto.UserRequestResponse;
import yaremko.yaromyr.entity.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderResponse {

    private String address;

    private LocalDateTime dateTime;

    private UserRequestResponse userResponse;

    private List<GoodsOrderResponse> goodsOrders = new ArrayList<>();

    public OrderResponse(Order order) {
        this.address = order.getAddress();
        this.dateTime = order.getDateTime();
        this.userResponse = new UserRequestResponse(order.getUser());
        this.goodsOrders = order.getGoodsOrders().stream().map(GoodsOrderResponse::new).collect(Collectors.toList());
    }
}
