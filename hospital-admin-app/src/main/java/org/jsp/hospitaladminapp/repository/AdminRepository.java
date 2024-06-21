package org.jsp.hospitaladminapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitaladminapp.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	List<Admin> findByName(String name);
	Optional<Admin> findByPhoneAndPassword(long phone,String password);

}
