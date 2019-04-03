package yaremko.yaromyr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "alcoholOrder")
@Getter @Setter
public class Order extends IdHolder{

    private String address;

    private LocalDateTime dateTime;

    private Status status;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order")
    private List<GoodsOrder> goodsOrders = new ArrayList<>();

    public enum Status {
        STATUS_ARCHIVED, STATUS_UNARCHIVED;
    }

}
