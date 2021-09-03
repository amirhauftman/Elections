package listeners;

import java.util.ArrayList;
import model.Department;
import model.Employee;
import model.Role;

public interface ListenerModel {

	 void addedDepartmentToModel(Department department);
	 void addedRoleToModel(Role role);
	 void addedEmployeeToModel(Employee employee);

	
	 
	 

	
}
