package mypack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mypack.model.ListImages;
import mypack.model.User;

@Repository
public interface ListImagesRepository extends JpaRepository<ListImages, Long> {
	List<ListImages> findByUser(User user);
}
