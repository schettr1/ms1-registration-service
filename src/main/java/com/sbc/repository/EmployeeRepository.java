package com.sbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbc.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
