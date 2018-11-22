package com.example.repository.ds3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entitys3.Functions;

@Repository("functionRepository")
public interface FunctionRepository extends CrudRepository<Functions, Integer> {

}
