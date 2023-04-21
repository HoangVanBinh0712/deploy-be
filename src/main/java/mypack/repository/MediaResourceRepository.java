package mypack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mypack.model.MediaResource;

public interface MediaResourceRepository extends JpaRepository<MediaResource, Long> {
	@Query(value = "SELECT * FROM media_resource WHERE id=:id", nativeQuery = true)
	public List<MediaResource> findMediaResourceById(@Param("id") Long id);
}
