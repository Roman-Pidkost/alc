package yaremko.yaromyr.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DataResponse<T> {

    private List<T> content;

    private PaginationResponse pagination;

    public DataResponse(List<T> data, PaginationResponse pagination) {
        this.content = data;
        this.pagination = pagination;
    }

    public DataResponse(List<T> data){
        this.content = data;
        this.pagination = null;
    }


}
