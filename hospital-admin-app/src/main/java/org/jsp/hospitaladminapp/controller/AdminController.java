package org.jsp.hospitaladminapp.controller;

import java.util.List;

import org.jsp.hospitaladminapp.dto.Admin;
import org.jsp.hospitaladminapp.dto.ResponseStructure;
import org.jsp.hospitaladminapp.repository.AdminRepository;
import org.jsp.hospitaladminapp.service.AdminService;
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
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/admins")
public class AdminController {
	@Autowired
	private AdminService as;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin)
	{
		return as.saveAdmin(admin);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin)
	{
		return as.updateAdmin(admin);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Admin>> findById(@PathVariable(name="id") int id)
	{
		return as.findById(id);
	}
	
	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Admin>>> findByName(@PathVariable(name="name") String name)
	{
		return as.findByName(name);
	}
	
	@PostMapping("/find-by-phone")
	public ResponseEntity<ResponseStructure<Admin>> verify(@RequestParam long phone,@RequestParam String password)
	{
		return as.verify(phone, password);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> delete( @PathVariable(name="id") int  id)
	{
		return as.delete(id);
		
	}

}
