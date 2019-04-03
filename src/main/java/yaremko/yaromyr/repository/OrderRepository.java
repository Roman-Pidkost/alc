package yaremko.yaromyr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yaremko.yaromyr.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    public Page<Order> findAllByStatusEquals(Order.Status status, Pageable pageable);
}
