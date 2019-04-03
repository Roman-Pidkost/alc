package yaremko.yaromyr.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter @Setter
public class PaginationRequest {

    private Integer page;

    private Integer size;

    private SortRequest sort;

    public PageRequest getPageRequest() {
        PageRequest pageRequest;
        if (sort == null) {
            pageRequest = PageRequest.of(page, size);
        } else {
            pageRequest = PageRequest.of(page, size, Sort.by(sort.getDirection(), sort.getFieldName()));
        }
        return pageRequest;
    }
}
