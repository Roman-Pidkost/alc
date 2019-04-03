package yaremko.yaromyr.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class TypeRequest {

    @NotNull
    private String name;

    @NotNull
    private Boolean maturity;

}
