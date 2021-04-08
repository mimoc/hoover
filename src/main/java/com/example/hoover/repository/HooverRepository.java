package com.example.hoover.repository;

import com.example.hoover.entity.HooverEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HooverRepository extends CrudRepository<HooverEntity, Long> {

}
