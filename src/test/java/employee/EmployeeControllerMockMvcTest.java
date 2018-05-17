package employee;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerMockMvcTest {

	@Resource
	private MockMvc mvc;

	@MockBean
	private EmployeeRepository employeeRepo;

	@Mock
	private Employee employee;

	@Test
	public void shouldRouteToSingleEmployeeView() throws Exception {
		long empId = 1;
		when(employeeRepo.findById(empId)).thenReturn(Optional.of(employee));
		mvc.perform(get("/employee?id=1")).andExpect(view().name(is("employee")));
	}

	@Test
	public void shouldBeOkForSingleEmployee() throws Exception {
		long empId = 1;
		when(employeeRepo.findById(empId)).thenReturn(Optional.of(employee));
		mvc.perform(get("/employee?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutSingleEmployeeIntoModel() throws Exception {
		when(employeeRepo.findById(1L)).thenReturn(Optional.of(employee));

		mvc.perform(get("/employee?id=1")).andExpect(model().attribute("employees", is(employee)));
	}
}