package xyz.hugme.hugmebackend.api.counselorlist.domain.repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.hugme.hugmebackend.api.counselorlist.domain.List;

public interface ListRepository extends JpaRepository<List, Long> {
    java.util.List<List> findAll();
    Slice<List> findAllBy(Pageable pageable);
}
