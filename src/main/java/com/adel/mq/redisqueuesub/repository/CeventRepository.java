package com.adel.mq.redisqueuesub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adel.mq.redisqueuesub.model.Cevent;
import java.util.List;


@Repository
public interface CeventRepository extends CrudRepository<Cevent, String>{

    Cevent findByToken(@Param("token") String token);
}
