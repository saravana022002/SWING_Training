package net.saravana.services;

import net.saravana.entities.Employee;
import net.saravana.exceptions.ResourceNotFoundException;
import net.saravana.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return  employeeRepository.findAll();
    }
    public Employee createEmployee(Employee employee){
        return  employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
    }
    public void deleteEmployee(Employee employee){
        employeeRepository.delete(employee);
    }
}
