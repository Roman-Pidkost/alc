package yaremko.yaromyr.dto.response;

import lombok.Getter;
import lombok.Setter;
import yaremko.yaromyr.entity.GoodsOrder;

@Getter
@Setter
public class GoodsOrderResponse {

    private GoodsResponse goodsResponse;

    private Long count;

    public GoodsOrderResponse(GoodsOrder goodsOrder) {
        this.goodsResponse = new GoodsResponse(goodsOrder.getGoods());
        this.count = goodsOrder.getCount();
    }
}
