package yaremko.yaromyr.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter
public class GoodsOrder extends IdHolder{

    private Long count;

    @ManyToOne
    private Goods goods;

    @ManyToOne
    private Order order;
}
