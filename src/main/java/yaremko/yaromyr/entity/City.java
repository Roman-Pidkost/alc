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
@Getter  @Setter
public class City extends IdHolder{

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "city")
    private List<User> userList = new ArrayList<>();
}
