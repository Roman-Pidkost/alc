package yaremko.yaromyr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yaremko.yaromyr.entity.Type;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {

    List<Type> findByNameLike(String name);

}
