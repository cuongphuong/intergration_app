package com.example.service.ds2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entitys2.BenefitPlan;
import com.example.repository.ds2.BenefitPlanRepository;


@Service("benefitPlanService")
public class BenefitPlanService {
	@Autowired
	private BenefitPlanRepository benefitPlanRepository;
	
	public Iterable<BenefitPlan> findAll(){
		return benefitPlanRepository.findAll();
	}
}
