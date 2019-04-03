package yaremko.yaromyr.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter
public class Firm extends IdHolder{

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Country country;


}
