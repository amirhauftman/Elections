package model;

import java.util.ArrayList;
import controller.Controller;
import listeners.ListenerModel;

public class Company {

	private static final int PROFIT_PER_HOUR=10; 
	private static final int BASE_RATE=30;
	private static final int HOURS_PER_MONTH=160; 
	private String name;
	private ArrayList<ListenerModel> allListener = new ArrayList<>();
	private ArrayList<Department> allDempartmantList = new ArrayList<>();


	public boolean addDepartment(Department d) {
		allDempartmantList.add(d);
		fireAddDepartmentToModel(d);
		return true;
	}

	public Company() {
		this.name = "company";
		this.allDempartmantList =allDempartmantList;
	}

	public String getName() {
		return name;
	}

	public int getNumOfDepartment(){
		return allDempartmantList.size();
	}

	public void setName(String name) {
		this.name = name;
	}

	/*private Department getDepartment(Department department ) {
		int index=allDempartmantList.indexOf(department);
		return this.allDempartmantList.get(index);
	}*/

	/*private Role<? extends Employee> getRole( Role<? extends Employee>roles, Department department ) {
		int index= getDepartment(department).getRoles().indexOf(roles);
		return getDepartment(department).getRoles().get(index);
	}*/

	public boolean addRole(int index, Role role) {
		allDempartmantList.get(index).addRole(role);
		return true;
	}
	public Department getDepartmentById(int id) {
		for (int i = 0; i < allDempartmantList.size(); i++) {
			Department tempD = allDempartmantList.get(i);
			if (tempD.getId()== id) {
				return tempD;
			}
		}
		return null;
	}

	public <T extends Employee>  void addEmployee(int depIndex, int roleIndex, T employee) { 
		allDempartmantList.get(depIndex).getRoles().get(roleIndex).addEmployee(employee);
		//	((Role <Employee>)getRole(role, department)).addEmployee(employee);
		//	((Role <Employee>)getRole(role, department)).addEmployee(employee);
	}

	public Employee findEmployee(int id) {
		for (int i = 0; i < allDempartmantList.size(); i++) {
			Department tempD =allDempartmantList.get(i);
			tempD.getRoles();
			ArrayList<Role> tempR = tempD.getRoles();
			for (int j = 0; j < tempR.size(); j++) {
				tempR.get(i);
				ArrayList<? extends Employee> tempE = tempR.get(i).getEmployees();
				for (int k = 0; k < tempE.size(); k++) {
					if (tempE.get(k).getId() == id) {
						return tempE.get(k);
					}
				}
			}
		}
		return null;
	}

	public boolean isEmployeeExist(int id) {
		if (findEmployee(id)==null) {
			return false;
		}
		return true;
	}

	private double calculateProfit() {
		double companyProfit=0;
		for (int i = 0; i < allDempartmantList.size(); i++) {
			companyProfit+= allDempartmantList.get(i).getDepartmentProfit();
		}
		return companyProfit;
	}

	public void registerListener(Controller controller) {
		allListener.add(controller);	
	}

	private void fireAddDepartmentToModel(Department department) {
		for (ListenerModel l : allListener) {
			l.addedDepartmentToModel(department);
		}

	}
	private void fireAddRoleToModel(Role role) {
		for (ListenerModel l : allListener) {
			l.addedRoleToModel(role);
		}
	}

	private void fireAddEmployeeToModel(Employee employee) {
		for (ListenerModel l : allListener) {
			l.addedEmployeeToModel(employee);
		}

	}
	/*
	public String toString() {
		StringBuffer sb =new StringBuffer();
		sb.append("Department name: " + name + "\n");
		sb.append("is the department syncronize: "+isSyncronize + "\n");
		for (int i = 0; i < allDempartmantList.size(); i++) {
			sb.append("role : " + allDempartmantList.get(i).getName()+ "\n");
			for (int j = 0; j <allDempartmantList.get; j++) {

			}
		}

	 */
	public String toString() {
		StringBuffer sb =new StringBuffer();
		//sb.append("Department name: " + name + "\n");
		//	sb.append("is the department syncronize: "+isSyncronize + "\n");
		for (int i = 0; i < allDempartmantList.size(); i++) {
			sb.append("role : " + allDempartmantList.get(i).getName()+ "\n"+
					" id Department :" + allDempartmantList.get(i).getId() + ", isSyncronize :" +allDempartmantList.get(i).getIsSyncronize() +  ", workPreference" +allDempartmantList.get(i).getPreference());

		}
		return sb.toString();
	}
}
