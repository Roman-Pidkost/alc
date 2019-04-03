package yaremko.yaromyr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yaremko.yaromyr.entity.Goods;
import yaremko.yaromyr.entity.GoodsOrder;

@Repository
public interface GoodsOrderRepository extends JpaRepository<GoodsOrder,Long>{
}
