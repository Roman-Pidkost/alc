package yaremko.yaromyr.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchGoodsRequest {

    private PaginationRequest paginationRequest;

    private String searchName = "";

    private Integer minPrice = 0;

    private Integer maxPrice = Integer.MAX_VALUE;

    private Double minVolume = 0.0;

    private Double maxVolume = Double.MAX_VALUE;

    private List<Long> firmsId = new ArrayList<>();

    private List<Long> countriesId = new ArrayList<>();

}
