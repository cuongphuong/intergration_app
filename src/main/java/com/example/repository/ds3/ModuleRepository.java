package com.example.repository.ds3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entitys3.Modules;

@Repository("moduleRepository")
public interface ModuleRepository extends CrudRepository<Modules, Integer>{

}
