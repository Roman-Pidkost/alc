package yaremko.yaromyr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import yaremko.yaromyr.entity.Goods;

@Repository
public interface GoodsRepository extends JpaRepository<Goods,Long>, JpaSpecificationExecutor<Goods> {
}
