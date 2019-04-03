package yaremko.yaromyr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Goods extends IdHolder{

    @Column(unique = true)
    private String name;

    private Double capacity;

    private Integer strength;

    private Integer maturity;

    private Integer price;

    private String description;

    private String pathToImage;

    @ManyToOne
    private SubType subType;

    @ManyToOne
    private Firm firm;

    @OneToMany(mappedBy = "goods")
    private List<GoodsOrder> goodsOrderList = new ArrayList<>();
}
