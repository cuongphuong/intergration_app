package com.example.service.ds1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entitys1.PayRates;
import com.example.repository.ds1.PayRateRepository;

@Service("payRateService")
public class PayRateService {
	
	@Autowired
	private PayRateRepository payRateRepository;
	
	public List<PayRates> findAll(){
		return (List<PayRates>) payRateRepository.findAll();
	}
	
	public Optional<PayRates> findByID(int ID) {
		return payRateRepository.findById(ID);
	}
	
	public PayRates save(PayRates payrate) {
		return payRateRepository.save(payrate);
	}
	
	public void deleteByID(int id) {
		payRateRepository.deleteById(id);
	}
}
