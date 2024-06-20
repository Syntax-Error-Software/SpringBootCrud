package org.jsp.springbootcrud.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.springbootcrud.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public List<Admin> findByName(String name);
	Optional<Admin> findByPhoneAndPassword(long phone ,String password);

}
