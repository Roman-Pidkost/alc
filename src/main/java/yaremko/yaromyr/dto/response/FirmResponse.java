package yaremko.yaromyr.dto.response;


import lombok.Getter;
import lombok.Setter;
import yaremko.yaromyr.entity.Firm;

@Getter
@Setter
public class FirmResponse {

    private Long id;

    private String name;

    private CountryResponse countryResponse;

    public FirmResponse(Firm firm) {
        this.id = firm.getId();
        this.name = firm.getName();
        this.countryResponse = new CountryResponse(firm.getCountry());
    }
}
