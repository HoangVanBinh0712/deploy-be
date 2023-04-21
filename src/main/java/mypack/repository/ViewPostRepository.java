package mypack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mypack.model.ViewPost;

@Repository
public interface ViewPostRepository extends JpaRepository<ViewPost, Long> {

}
