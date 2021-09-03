package controller;

import listeners.ListenerModel;
import listeners.ListenerView;
import model.BadInputException;
import model.Company;
import model.Department;
import model.Employee;
import model.EmployeeBaseRate;
import model.EmployeeHourly;
import model.EmployeeSales;
import model.Role;
import model.WorkPreference;
import view.AbstractView;
import javafx.event.EventHandler;
import java.util.ArrayList;
import javafx.event.ActionEvent;

public class Controller implements ListenerModel, ListenerView {

	private Company company;
	private AbstractView companyView;

	public Controller(Company company, AbstractView view) {
		this.company = company;
		companyView = view;

		company.registerListener(this);
		view.registerListener(this);
	}
	/*
	EventHandler<ActionEvent> exitButtonEvent = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent arg0) {
			//view.exitButtonScreen(m);
			try {
				ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("Company.data"));
				outFile.writeObject(company.getName());
				outFile.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				//view.getSecondaryScreen().setAlwaysOnTop(true);
			} 
			//view.getSecondaryScreen().close();
			companyView.getStg().close();
		}
	};
	companyView.exitButtonEvent(exitButtonEvent);
	 */

	@Override
	public void addDepartmentFromView(String name, boolean isSync, int preference) {
		if (preference==0) {
			Department department = new Department(isSync, name, WorkPreference.noChange);
			company.addDepartment(department);
		}
		else {
			if (preference==1) {
				Department department = new Department(isSync, name, WorkPreference.workFromHome);
				company.addDepartment(department);
			}

			else {
				if (preference==2) {
					Department department = new Department(isSync, name, WorkPreference.startEarly);
					company.addDepartment(department);
				}

				else {
					Department department = new Department(isSync, name, WorkPreference.startLate);
					company.addDepartment(department);
				}
			}
		}
	}

	@Override
	public void addRoleFromView(String name, boolean isSync, int preference, int index) {
		if (preference==0) {
			Role role = new Role(isSync, name,WorkPreference.noChange );
			company.addRole(index, role);
		}
		else {
			if (preference==1) {
				Role role = new Role(isSync, name, WorkPreference.workFromHome);
				company.addRole(index, role);
			}

			else {
				if (preference==2) {
					Role role = new Role(isSync, name, WorkPreference.startEarly);
					company.addRole(index, role);
				}

				else {
					Role role = new Role(isSync, name, WorkPreference.startLate);
					company.addRole(index, role);
				}
			}
		}
	}

	@Override
	public void addedDepartmentToModel(Department department) {
		companyView.addDeprtmantToView(department);

	}

	@Override
	public void addedRoleToModel(Role role) {
		companyView.addRoleToView(role);
	}



	@Override
	public void addEmployeeFromView(String name, boolean isSync, int preference, int id, int depIndex, int roleIndex, String workerType) throws BadInputException {
		if (preference==0) {
			if (workerType == "EmployeeBaseRate") {
				Employee EmployeeBaseRate = new EmployeeBaseRate(id, name, WorkPreference.noChange);
				company.addEmployee(depIndex, roleIndex,EmployeeBaseRate);
			}
			else {
				if (workerType == "EmployeeHourly") {
					Employee EmployeeHourly = new EmployeeHourly(id, name, WorkPreference.noChange);
					company.addEmployee(depIndex, roleIndex,EmployeeHourly);
				}
				else {
					Employee EmployeeSales = new EmployeeSales(id, name,  WorkPreference.noChange);
					company.addEmployee(depIndex, roleIndex, EmployeeSales);
				}
			}
		}
		else {
			if (preference==1) {
				if (workerType == "EmployeeBaseRate") {
					Employee EmployeeBaseRate = new EmployeeBaseRate(id, name, WorkPreference.workFromHome);
					company.addEmployee(depIndex, roleIndex,EmployeeBaseRate);
				}
				else {
					if (workerType == "EmployeeHourly") {
						Employee EmployeeHourly = new EmployeeHourly(id, name, WorkPreference.workFromHome);
						company.addEmployee(depIndex, roleIndex,EmployeeHourly);
					}
					else {
						Employee EmployeeSales = new EmployeeSales(id, name, WorkPreference.workFromHome);
						company.addEmployee(depIndex, roleIndex, EmployeeSales);
					}
				}
			}
			else {
				if (preference==2) {
					if (workerType == "EmployeeBaseRate") {
						Employee EmployeeBaseRate = new EmployeeBaseRate(id, name, WorkPreference.startEarly);
						company.addEmployee(depIndex, roleIndex,EmployeeBaseRate);
					}
					else {
						if (workerType == "EmployeeHourly") {
							Employee EmployeeHourly = new EmployeeHourly(id, name, WorkPreference.startEarly);
							company.addEmployee(depIndex, roleIndex,EmployeeHourly);
						}
						else {
							Employee EmployeeSales = new EmployeeSales(id, name, WorkPreference.startEarly);
							company.addEmployee(depIndex, roleIndex, EmployeeSales);
						}
					}
				}
				else {
					if (workerType == "EmployeeBaseRate") {
						Employee EmployeeBaseRate = new EmployeeBaseRate(id, name, WorkPreference.startLate);
						company.addEmployee(depIndex, roleIndex,EmployeeBaseRate);
					}
					else {
						if (workerType == "EmployeeHourly") {
							Employee EmployeeHourly = new EmployeeHourly(id, name, WorkPreference.startLate);
							company.addEmployee(depIndex, roleIndex,EmployeeHourly);
						}
						else {
							Employee EmployeeSales = new EmployeeSales(id, name, WorkPreference.startLate);
							company.addEmployee(depIndex, roleIndex, EmployeeSales);
						}
					}
				}
			}
		}
	}

	@Override
	public void addedEmployeeToModel(Employee employee) {
		companyView.addEmployeeToView(employee);

	}

	@Override
	public ArrayList<String> getRolesFromModel(int depIndex) {

		ArrayList<String> back = new ArrayList<>();

		for (int i=0; i<company.getDepartmentById(depIndex).getRoles().size(); i++) {
			back.add(company.getDepartmentById(depIndex).getRoles().get(i).getName());
		}

		return back;

	}
}
