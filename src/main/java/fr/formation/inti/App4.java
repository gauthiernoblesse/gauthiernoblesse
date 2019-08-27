package fr.formation.inti;

import java.util.Date;
import java.util.List;

import fr.formation.inti.entities.Employee;
import fr.formation.inti.service.EmployeeService;

public class App4 {

	public static void main(String[] args) {
		EmployeeService service = new EmployeeService();
		
		List<Employee> list = service.findAll();
		for(Employee e : list)
			System.out.println("#### "+e);
		Employee emp = service.findById(3);
		System.out.println(emp);

		Employee emp2 = new Employee();
		emp2.setDepartment(emp.getDepartment());
		emp2.setEmployees(emp.getEmployees());
		emp2.setFirstName("Jean");
		emp2.setLastName("Cadeau");
		emp2.setManager(emp.getManager());
		emp2.setStartDate(new Date());
		
		list = service.findAll();
		System.out.println("Nombre employees :"+ list.size());
		Integer id = service.save(emp2);
		
		System.out.println("emp2 save id :"+ id);
		list = service.findAll();
		System.out.println("Nombre after save employees :"+ list.size());
		service.delete(id);
		list = service.findAll();
		System.out.println("Nombre after delete employees :" + list.size());
//		System.out.println("emp2 persist ? "+ session.contains(emp2));
		
	}

}
