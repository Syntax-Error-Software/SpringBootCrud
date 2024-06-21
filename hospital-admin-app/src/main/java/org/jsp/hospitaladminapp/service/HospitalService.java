package org.jsp.hospitaladminapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitaladminapp.dto.Admin;
import org.jsp.hospitaladminapp.dto.Hospital;
import org.jsp.hospitaladminapp.dto.ResponseStructure;
import org.jsp.hospitaladminapp.repository.AdminRepository;
import org.jsp.hospitaladminapp.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {
	@Autowired
	private HospitalRepository hr;
	@Autowired
	private AdminRepository adminRepository;
	
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(Hospital hospital,int admin_id)
	{
		ResponseStructure<Hospital> structure=new ResponseStructure<>();
		Optional<Admin> recAdmin=adminRepository.findById(admin_id);
		if(recAdmin.isPresent())
		{
			Admin a=recAdmin.get();
			a.getHospitals().add(hospital);
			hospital.setAdmin(a);
			adminRepository.save(a);
			structure.setMessage("Hospital Saved Successfully");
			structure.setData(hr.save(hospital));
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		structure.setMessage("Hospital not Saved");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
	
    public ResponseEntity<ResponseStructure<Hospital>> findById(int id)
    {
    	Optional<Hospital> recHospital=hr.findById(id);
    	ResponseStructure<Hospital> s=new ResponseStructure<>();
    	if(recHospital.isPresent())
    	{
    		s.setMessage("hospital id found");
    		s.setData(recHospital.get());
    		s.setStatusCode(HttpStatus.OK.value());
    		return ResponseEntity.status(HttpStatus.OK).body(s);
    	}
    	else
    	{
    		s.setMessage("hospital id not found");
    		s.setData(null);
    		s.setStatusCode(HttpStatus.NOT_FOUND.value());
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(s);
    	}
    }
    
    public ResponseEntity<ResponseStructure<Hospital>> updateHospital(Hospital hospital)
    {
    	Optional<Hospital> recHospital=hr.findById(hospital.getId());
    	ResponseStructure<Hospital> s=new ResponseStructure<>();
    	if(recHospital.isPresent())
    	{
    		Hospital dbhosHospital=recHospital.get();
    		dbhosHospital.setName(hospital.getName());
    		dbhosHospital.setEstb(hospital.getEstb());
    		dbhosHospital.setFounder(hospital.getFounder());
    		dbhosHospital.setGst(hospital.getGst());
    		s.setMessage("Hospital detalis are updated");
    		s.setData(hr.save(dbhosHospital));
    		s.setStatusCode(HttpStatus.ACCEPTED.value());
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(s);
    	}
    	else
    	{
    		s.setMessage("Hospital detalis are not updated");
    		s.setData(null);
    		s.setStatusCode(HttpStatus.NOT_FOUND.value());
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(s);
    		
    	}
    }
    
    public ResponseEntity<ResponseStructure<List<Hospital>>> findByName(String name)
	{
		ResponseStructure<List<Hospital>> s=new ResponseStructure<>();
		List<Hospital> admins=hr.findByName(name);
		
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
    
    public ResponseEntity<ResponseStructure<List<Hospital>>> findByAdminId(int admin_id)
	{
		ResponseStructure<List<Hospital>> s=new ResponseStructure<>();
		List<Hospital> recHospital=hr.findByAdminId(admin_id);
		
		if(recHospital.isEmpty())
		{
			s.setMessage("Admin id is wrong");
			s.setData(null);
			s.setStatusCode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(s);
		}
		else
		{
			s.setMessage("Admin details are found ");
			s.setData(recHospital);
			s.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(s);
			
		}
		
	}
    public ResponseEntity<ResponseStructure<List<Hospital>>> findByAdmin(long phone,String password)
   	{
   		ResponseStructure<List<Hospital>> s=new ResponseStructure<>();
   		List<Hospital> recHospital=hr.findByAdmin(phone,password);
   		
   		if(recHospital.isEmpty())
   		{
   			s.setMessage("Admin id is wrong");
   			s.setData(null);
   			s.setStatusCode(HttpStatus.NOT_FOUND.value());
   			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(s);
   		}
   		else
   		{
   			s.setMessage("Admin details are found ");
   			s.setData(recHospital);
   			s.setStatusCode(HttpStatus.OK.value());
   			return ResponseEntity.status(HttpStatus.OK).body(s);
   			
   		}
   		
   	}
    
    public ResponseEntity<ResponseStructure<String>> delete(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Hospital> recAdmin = hr.findById(id);
		if (recAdmin.isPresent()) {
			structure.setData("hospital Found");
			structure.setMessage("hospital Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			hr.delete(recAdmin.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		structure.setData("hospital Not Found");
		structure.setMessage("Cannot delete hospital as Id is Invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
    
}
