package employee;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController underTest;

	@Mock
	private EmployeeRepository employeeRepo;

	@Mock
	private Employee employee;
	
	@Mock
	private Employee secondEmployee;
	
	@Mock
	private Model model;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddAllEmployeesToModel() {
		Collection<Employee> allEmployees = Arrays.asList(employee, secondEmployee);
		when(employeeRepo.findAll()).thenReturn(allEmployees);

		underTest.findAllEmployees(model);

		verify(model).addAttribute("employees", allEmployees);
	}

	@Test
	public void shouldAddEmployeeToModel() {
		long empId = 1;
		when(employeeRepo.findById(empId)).thenReturn(Optional.of(employee));

		underTest.findOneEmployee(empId, model);

		verify(model).addAttribute("employees", employee);
	}
	
	
	
}