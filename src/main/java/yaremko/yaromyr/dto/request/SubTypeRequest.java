package yaremko.yaromyr.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class SubTypeRequest {

    @NotNull
    private String name;

    @NotNull
    private Long typeId;

}
