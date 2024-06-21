package org.jsp.hospitaladminapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitaladminapp.dto.Admin;
import org.jsp.hospitaladminapp.dto.Hospital;
import org.jsp.hospitaladminapp.dto.ResponseStructure;
import org.jsp.hospitaladminapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class AdminService {
	@Autowired
	private AdminRepository ar;
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin)
	{
		ResponseStructure<Admin> s=new ResponseStructure<>();
		s.setMessage("Admin saved successfull");
		s.setData(ar.save(admin));
		s.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(s);
	}
	
	public ResponseEntity<ResponseStructure<Admin>> findById( int id)
	{
		Optional<Admin> recAdmin=ar.findById(id);
		ResponseStructure<Admin> s=new ResponseStructure<>();
		if(recAdmin.isPresent())
		{
			s.setMessage("details are found");
			s.setData(recAdmin.get());
			s.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(s);
		}
		else
		{
			s.setMessage("details are not found");
			s.setData(null);
			s.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(s);
			
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin)
	{
		ResponseStructure<Admin> s=new ResponseStructure<>();
		Optional<Admin> recAdmin=ar.findById(admin.getId());
		if(recAdmin.isPresent())
		{
			Admin dbAdmin=recAdmin.get();
			dbAdmin.setEmail(admin.getEmail());
			dbAdmin.setName(admin.getName());
			dbAdmin.setPassword(admin.getPassword());
			dbAdmin.setPhone(admin.getPhone());
			s.setMessage("details are updated");
			s.setData(ar.save(admin));
			s.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(s);
		}
		else
		{
			s.setMessage("details are not updated");
			s.setData(null);
			s.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(s);
		}
	
	}
	
	
	public ResponseEntity<ResponseStructure<List<Admin>>> findByName(String name)
	{
		ResponseStructure<List<Admin>> s=new ResponseStructure<>();
		List<Admin> admins=ar.findByName(name);
		
		if(admins.isEmpty())
		{
			s.setData(null);
			s.setMessage("details are not found");
			s.setStatusCode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(s);
		
		}
		s.setMessage("name matched");
		s.setData(admins);
		s.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(s);
		
	}
	
	public  ResponseEntity<ResponseStructure<Admin>> verify(long phone,String password)
	{
		ResponseStructure<Admin> s=new ResponseStructure<>();
		Optional<Admin> recAdmin=ar.findByPhoneAndPassword(phone,password);
		if(recAdmin.isPresent())
		{
			s.setData(recAdmin.get());
			s.setMessage("phone and password are matched");
			s.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(s);
			
		}
		s.setData(null);
		s.setMessage("phone and password are not matched");
		s.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(s);
		
	}
	public ResponseEntity<ResponseStructure<String>> delete(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = ar.findById(id);
		if (recAdmin.isPresent()) {
			structure.setData("Admin Found");
			structure.setMessage("Admin Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			ar.delete(recAdmin.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		structure.setData("Admin Not Found");
		structure.setMessage("Cannot delete Admin as Id is Invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
	
	
		

}
