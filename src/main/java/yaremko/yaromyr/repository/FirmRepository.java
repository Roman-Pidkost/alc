package yaremko.yaromyr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yaremko.yaromyr.entity.Firm;

import java.util.List;

@Repository
public interface FirmRepository extends JpaRepository<Firm,Long>{

    List<Firm> findByCountryId(Long id);

    List<Firm> findByNameLike(String name);
}
