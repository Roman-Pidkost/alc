package yaremko.yaromyr.dto.response;

import lombok.Getter;
import lombok.Setter;
import yaremko.yaromyr.entity.SubType;

@Getter @Setter
public class SubTypeResponse {

    private Long id;

    private String name;

    private String typeName;

    public SubTypeResponse(SubType subType) {
        this.id = subType.getId();
        this.name = subType.getName();
        this.typeName = subType.getType().getName();
    }
}
