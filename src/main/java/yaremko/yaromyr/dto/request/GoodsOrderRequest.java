package yaremko.yaromyr.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsOrderRequest {

    private Long goodsId;

    private Long count;
}
