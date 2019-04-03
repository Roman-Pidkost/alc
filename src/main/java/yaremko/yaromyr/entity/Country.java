package yaremko.yaromyr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Country extends IdHolder {

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "country")
    private List<City> cityList = new ArrayList<>();

    @OneToMany(mappedBy = "country")
    private List<Firm> firmList = new ArrayList<>();
}
