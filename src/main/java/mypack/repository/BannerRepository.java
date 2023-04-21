package mypack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mypack.model.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long>{

}
