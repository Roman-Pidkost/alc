package yaremko.yaromyr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class IdHolder implements Serializable {

    private static final long serialVersionUID = -8445364236328209999L;

    @Id
    @GeneratedValue
    private Long id;

}
