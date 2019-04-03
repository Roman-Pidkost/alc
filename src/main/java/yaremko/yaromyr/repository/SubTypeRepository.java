package yaremko.yaromyr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yaremko.yaromyr.entity.SubType;

import java.util.List;

@Repository
public interface SubTypeRepository extends JpaRepository<SubType,Long>{

    Page<SubType> findByTypeId(Long id, Pageable pageable);
    List<SubType> findByTypeId(Long id);
    List<SubType> findByNameLike(String name);
}
