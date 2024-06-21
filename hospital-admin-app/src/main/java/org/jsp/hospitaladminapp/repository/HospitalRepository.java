package org.jsp.hospitaladminapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitaladminapp.dto.Admin;
import org.jsp.hospitaladminapp.dto.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

	List<Hospital> findByName(String name);

	List<Hospital> findByAdminId(int admin_id);

	@Query("select h from Hospital h where h.admin.phone=?1 and h.admin.password=?2")
	List<Hospital> findByAdmin(long phone, String password);

}
