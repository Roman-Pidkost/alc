package yaremko.yaromyr.dto.response;

import lombok.Getter;
import lombok.Setter;
import yaremko.yaromyr.entity.Type;

@Getter @Setter
public class TypeResponse {

    private Long id;

    private String name;

    private Boolean maturity;

    public TypeResponse(Type type) {
        this.id = type.getId();
        this.name = type.getName();
        this.maturity = type.getMaturity();
    }
}
