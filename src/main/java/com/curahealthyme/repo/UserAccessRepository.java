package com.curahealthyme.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.curahealthyme.model.UserAccess;
import com.curahealthyme.model.User_Logon;

public interface UserAccessRepository  extends CrudRepository<UserAccess, Long>{
	@Query("SELECT u FROM UserAccess u WHERE u.UserAccessId = ?1")
    public UserAccess findById(long id);
	
	@Query("SELECT u.UserAccessId FROM UserAccess u WHERE u.UserRole = ?1")
    public long findAccessIdByRole(String userrole);
}
