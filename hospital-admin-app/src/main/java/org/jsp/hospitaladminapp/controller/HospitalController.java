package org.jsp.hospitaladminapp.controller;

import java.util.List;

import org.jsp.hospitaladminapp.dto.Admin;
import org.jsp.hospitaladminapp.dto.Hospital;
import org.jsp.hospitaladminapp.dto.ResponseStructure;
import org.jsp.hospitaladminapp.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {
	@Autowired
	private HospitalService hs;
	
	@PostMapping("/{admin_id}")
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(@RequestBody Hospital hospital,@PathVariable int admin_id)
	{
		return hs.saveHospital(hospital, admin_id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Hospital>> updateAdmin(@RequestBody Hospital hospital)
	{
		return hs.updateHospital(hospital);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Hospital>> findById(@PathVariable(name="id") int id)
	{
		return hs.findById(id);
	}
	
	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Hospital>>> findByName(@PathVariable(name="name") String name)
	{
		return hs.findByName(name);
	}
	
	@GetMapping("/find-by-admin/{admin_id}")
	public ResponseEntity<ResponseStructure<List<Hospital>>> findByAdminId(@PathVariable(name="admin_id") int admin_id)
	{
		return hs.findByAdminId(admin_id);
	}
	
	
	@PostMapping("/find-by-admin")
	public ResponseEntity<ResponseStructure<List<Hospital>>> findByadmin(@RequestParam long phone,@RequestParam String password)
	{
		return hs.findByAdmin(phone, password);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> delete(@PathVariable(name="id") int id)
	{
		return hs.delete(id);
	}

}
