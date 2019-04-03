package yaremko.yaromyr.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class PaginationResponse {

    private Integer page;
    private Integer pageSize ;
    private Long total;

    public PaginationResponse() {
    }

    public PaginationResponse(Page page) {
        this.page = page.getNumber();
        this.pageSize = page.getNumberOfElements();
        this.total = page.getTotalElements();
    }

}
