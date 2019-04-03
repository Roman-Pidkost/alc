package yaremko.yaromyr.dto.response;

import lombok.Getter;
import lombok.Setter;
import yaremko.yaromyr.entity.Goods;

@Getter
@Setter
public class GoodsResponse {

    private Long id;

    private String name;

    private Double capacity;

    private Integer strength;

    private Integer maturity;

    private Integer price;

    private String description;

    private String pathToImage;

    private String subTypeName;

    private String firmName;

    public GoodsResponse(Goods goods) {
        this.id = goods.getId();
        this.name = goods.getName();
        this.capacity = goods.getCapacity();
        this.strength = goods.getStrength();
        this.maturity = goods.getMaturity();
        this.price = goods.getPrice();
        this.description = goods.getDescription();
        this.pathToImage = goods.getPathToImage();
        this.subTypeName = goods.getSubType().getName();
        this.firmName = goods.getFirm().getName();
    }
}
