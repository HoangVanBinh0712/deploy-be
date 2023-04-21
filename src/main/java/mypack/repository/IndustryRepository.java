package mypack.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.Industry;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long> {

	@Query(value = "select * from industry where name in (:lstName)", nativeQuery = true)
	List<Industry> getByListName(@Param("lstName") Set<String> lstName);

	@Query(value = "select * from industry where name like %:keyword% limit 1", nativeQuery = true)
	Industry getByNameLike(@Param("keyword") String keyword);
}
