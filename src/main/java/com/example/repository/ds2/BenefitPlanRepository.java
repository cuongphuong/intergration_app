package com.example.repository.ds2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entitys2.BenefitPlan;

@Repository("nenefitPlanRepository")
public interface BenefitPlanRepository extends CrudRepository<BenefitPlan, Long>{

}
