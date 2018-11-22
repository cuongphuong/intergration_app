package com.example.service.ds2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entitys2.JobHistory;
import com.example.repository.ds2.JobHistoryRepository;

@Service("jobHistoryService")
public class JobHistoryService {
	@Autowired
	private JobHistoryRepository jobHistoryRepository;
	
	public Iterable<JobHistory> findAll(){
		return jobHistoryRepository.findAll();
	}
}
