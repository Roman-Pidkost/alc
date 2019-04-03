package yaremko.yaromyr.dto.response;

import lombok.Getter;
import lombok.Setter;
import yaremko.yaromyr.entity.City;

@Getter
@Setter
public class CityResponse {

    private Long id;

    private String name;

    private String countryName;

    public CityResponse(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.countryName = city.getCountry().getName();
    }
}
