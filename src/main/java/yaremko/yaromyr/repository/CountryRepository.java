package yaremko.yaromyr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;
import yaremko.yaromyr.entity.Country;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long>{

    List<Country> findByNameLike(String name);
}
