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
public class Type extends IdHolder{

    @Column(unique = true)
    private String name;

    private Boolean maturity;

    @OneToMany(mappedBy = "type")
    private List<SubType> subTypeList = new ArrayList<>();


}
