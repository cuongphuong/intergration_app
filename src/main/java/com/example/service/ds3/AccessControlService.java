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
	
	public Iterable<AccessControl> findAllRolesByUser(int userID){
		return accessControlRepository.findAllRolesByUser(userID);
	}
	
	public AccessControl save(AccessControl accessControl) {
		return accessControlRepository.save(accessControl);
	}
	
	public Iterable<AccessControl> saveAll(Iterable<AccessControl> lstAccessControl) {
		return accessControlRepository.saveAll(lstAccessControl);
	}
	
	public void deleteAllByList(Iterable<AccessControl> lst) {
		accessControlRepository.deleteAll(lst);
	}
	
	public boolean check(AccessControlKey key) {
		return accessControlRepository.checkByDoubleKey(key.getFunctionID(), key.getUserID()) > 0;
	}
	
	public Optional<AccessControl> findByDoubleKey(AccessControlKey key) {
		return accessControlRepository.findByDoubleKey(key.getFunctionID(), key.getUserID());
	}
	
	public void deleteByDoubleKey(AccessControlKey key) {
		accessControlRepository.deleteByDoubleKey(key.getFunctionID(), key.getUserID());
	}
	
	public void delete(AccessControl acc) {
		accessControlRepository.delete(acc);
	}
}
