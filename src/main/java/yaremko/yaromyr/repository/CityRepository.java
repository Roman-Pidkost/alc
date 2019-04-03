package yaremko.yaromyr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yaremko.yaromyr.entity.City;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    List<City> findByNameLike(String name);
    List<City> findByCountryId(Long id);
    Page<City> findByCountryId(Long id, Pageable pageable);
}
