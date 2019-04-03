package yaremko.yaromyr.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import yaremko.yaromyr.dto.request.SearchGoodsRequest;
import yaremko.yaromyr.entity.Country;
import yaremko.yaromyr.entity.Goods;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsSpecification implements Specification<Goods> {

    private SearchGoodsRequest search;

    public GoodsSpecification(SearchGoodsRequest searchGoodsRequest) {
        this.search = searchGoodsRequest;
    }

    @Nullable
    @Override
    public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.between(root.get("capacity"), search.getMinVolume(), search.getMaxVolume()));
        predicates.add(criteriaBuilder.between(root.get("price"), search.getMinPrice(), search.getMaxPrice()));
        if (!search.getSearchName().isEmpty())
            predicates.add(criteriaBuilder.like(root.get("name"), search.getSearchName()));
        if (!search.getFirmsId().isEmpty()) {
            Join<Goods, Country> joinGoodsFirm = root.join("firm");
            predicates.add(joinGoodsFirm.get("id").in(search.getFirmsId()));
        }
        if (!search.getCountriesId().isEmpty()) {
            Join<Goods, Country> joinGoodsFirm = root.join("firm").join("country") ;
            predicates.add(joinGoodsFirm.get("id").in(search.getCountriesId()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


}
