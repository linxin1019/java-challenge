package jp.co.axa.apidemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    EmployeeService employeeService;

    Employee EMPLOYEE1 = new Employee(1l, "test1", 1000, "testDepart1");
    Employee UPDATED_EMPLOYEE1 = new Employee(1l, "test2", 2000, "testDepart2");
    Employee EMPLOYEE3 = new Employee(2l, "test3", 3000, "testDepart3");
    Employee EMPLOYEE4 = new Employee(3l, "test4", 4000, "testDepart4");
    Employee EMPLOYEE5 = new Employee(4l, "test5", 5000, "testDepart5");

    @Test
    public void getAllRecords_success() throws Exception {
        List<Employee> records = new ArrayList<>(Arrays.asList(EMPLOYEE1, EMPLOYEE3, EMPLOYEE4, EMPLOYEE5));

        Mockito.when(employeeService.retrieveEmployees(PageRequest.of(0, 5))).thenReturn(new PageImpl<>(records));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/getEmployees")
                        .content("{\"page\": 0, \"size\": 5}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode", is(0)))
                .andExpect(jsonPath("$.resultObjects.totalPages", is(1)))
                .andExpect(jsonPath("$.resultObjects.totalElements", is(4)))
                .andExpect(jsonPath("$.resultObjects.last", is(true)))
                .andExpect(jsonPath("$.resultObjects.content", hasSize(4)));
    }

    @Test
    public void getEmployeeById_success() throws Exception {
        Mockito.when(employeeService.getEmployee(EMPLOYEE1.getId())).thenReturn(EMPLOYEE1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode", is(0)))
                .andExpect(jsonPath("$.resultObjects.id", is(1)));
    }

    @Test
    public void saveEmployee_success() throws Exception {
        Employee employee = new Employee();
        employee.setDepartment("testDepart1");
        employee.setName("test1");
        employee.setSalary(1000);
        Mockito.when(employeeService.saveEmployee(employee)).thenReturn(EMPLOYEE1);
        JSONObject json = new JSONObject();
        json.put("name", "test1");
        json.put("salary", 1000);
        json.put("department", "testDepart1");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/employees")
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode", is(0)))
                .andExpect(jsonPath("$.resultObjects.id", is(1)));
    }

    @Test
    public void updateEmployee_success() throws Exception {
        Mockito.when(employeeService.getEmployee(EMPLOYEE1.getId())).thenReturn(EMPLOYEE1);
        Mockito.when(employeeService.updateEmployee(UPDATED_EMPLOYEE1)).thenReturn(UPDATED_EMPLOYEE1);
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("name", "test2");
        json.put("salary", 2000);
        json.put("department", "testDepart2");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/employees")
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode", is(0)))
                .andExpect(jsonPath("$.resultObjects", nullValue()));
    }

    @Test
    public void deleteEmployee_success() throws Exception {
        Mockito.when(employeeService.getEmployee(EMPLOYEE1.getId())).thenReturn(EMPLOYEE1);
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("name", "test2");
        json.put("salary", 2000);
        json.put("department", "testDepart2");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/employees")
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode", is(0)))
                .andExpect(jsonPath("$.resultObjects", nullValue()));
    }
}
