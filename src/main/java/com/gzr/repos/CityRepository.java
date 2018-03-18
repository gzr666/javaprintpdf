package com.gzr.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gzr.Models.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

}
