package yaremko.yaromyr.dto.request;

import lombok.Getter;
import lombok.Setter;
import yaremko.yaromyr.dto.UserRequestResponse;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private String address;

    private UserRequestResponse userRequest;

    private List<GoodsOrderRequest> goods = new ArrayList<>();


}
