package yaremko.yaromyr.dto.request;

import lombok.Getter;
import lombok.Setter;
import yaremko.yaromyr.entity.Firm;
import yaremko.yaromyr.entity.GoodsOrder;
import yaremko.yaromyr.entity.SubType;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class GoodsRequest {

    private String name;

    private Double capacity;

    private Integer strength;

    private Integer maturity;

    private Integer price;

    private String image;

    private String description;

    private Long subTypeId;

    private Long firmId;

}
