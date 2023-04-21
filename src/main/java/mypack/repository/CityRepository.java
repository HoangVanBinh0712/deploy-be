package mypack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mypack.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
