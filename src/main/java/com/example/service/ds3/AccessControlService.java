package com.example.service.ds3;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entitys3.AccessControl;
import com.example.entitys3.AccessControlKey;
import com.example.repository.ds3.AccessControlRepository;

@Service("accessControlService")
public class AccessControlService {
	@Autowired
	private AccessControlRepository accessControlRepository;
	
	public Iterable<AccessControl> findAll(){
		return accessControlRepository.findAll();
	}
	
	public boolean checkAuthor(AccessControlKey key) {
		Optional<AccessControl> access = accessControlRepository.findByDoubleKey(key.getFunctionID(), key.getUserID());
		if(access.isPresent() == true)
			if(access.get().isStatus() == true)
				return true;
		return false;
	}
}
