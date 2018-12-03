package com.example.repository.ds1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entitys1.PayRates;

@Repository("payRateRepository")
public interface PayRateRepository extends CrudRepository<PayRates, Integer>{
//	@Query()
//	public void deleteByUserName
}	
