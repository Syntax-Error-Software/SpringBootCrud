package org.jsp.hospitaladminapp.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.jsp.hospitaladminapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class SpringBootExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> handle(ConstraintViolationException exception)
	{
		ResponseStructure<String> s=new ResponseStructure<>();
		s.setMessage(exception.getErrorMessage());
		s.setData("can not be save the data");
		s.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(s);
	}
	

}
