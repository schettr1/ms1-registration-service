package com.sbc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbc.entity.Employee;
import com.sbc.exception.InvalidRequestException;
import com.sbc.repository.EmployeeRepository;

@Service
@Transactional
public class RegistrationService {

	@Autowired
	EmployeeRepository emp_repository;
	
	public Employee create(Employee employee) {
		return emp_repository.save(employee);		
	}

	public Employee getEmployee(int id) {
		return emp_repository.findById(id)
				.orElseThrow(
						() -> new InvalidRequestException("id=" + id + " cannot be found."));
	}
	
}
