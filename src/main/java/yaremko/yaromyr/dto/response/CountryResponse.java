package yaremko.yaromyr.dto.response;

import lombok.Getter;
import lombok.Setter;
import yaremko.yaromyr.entity.Country;

@Getter @Setter
public class CountryResponse {

    private Long id;

    private String name;

    public CountryResponse(Country country) {
        this.id = country.getId();
        this.name = country.getName();
    }
}
