package fr.formation.inti.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.formation.inti.entities.Employee;

public class EmployeeServiceTest {
	EmployeeService service;

	@Before
	public void init() {
		service = new EmployeeService();
	}

	@Test
	public void findById() {
		Employee emp = service.findById(3);
		assertNotNull(emp);

	}
	@Test
	public void findAll() {
//		List<Employee> list = service.findAll();
//		assertEquals("List Employee", 24, list.size());	

	}

	@Test
	public void saveAndDelete() {
		Employee emp = service.findById(3);
		
	
		Employee emp2 = new Employee();
		emp2.setDepartment(emp.getDepartment());
		emp2.setEmployees(emp.getEmployees());
		emp2.setFirstName("Jean");
		emp2.setLastName("Cadeau");
		emp2.setManager(emp.getManager());
		emp2.setStartDate(new Date());
		assertNull(emp2.getEmpId());
		Integer id = service.save(emp2);
		assertNotNull(emp2.getEmpId());

		// delete
		service.delete(id);
		Employee empdeleted = service.findById(id);

		assertNull(empdeleted);
	}

}
