package view;

import java.io.FileOutputStream;

import controller.Controller;
import model.Department;
import model.Employee;
import model.Role;

public interface AbstractView {

	void registerListener(Controller controller);

	void addDeprtmantToView(Department department);

	void addRoleToView(Role role);

	void addEmployeeToView(Employee employee);

	//FileOutputStream getStg();

	
	
	

}
