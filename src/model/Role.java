package model;

import java.util.ArrayList;

public class Role implements Prefenceable, Syncronizable {

	private static int counter = 0;
	private int idRole;
	private boolean isSyncronize;
	private String name;
	private ArrayList<Employee> employees;
	private WorkPreference workPreference;

	public WorkPreference getPreference() {
		return workPreference;
	}

	public int getIdRole() {
		return idRole;
	}

	public boolean isSyncronize() {
		return isSyncronize;
	}

	public void setisSyncronize(boolean isSyncronize) {
		this.isSyncronize = isSyncronize;
	}

	public String getName() {
		return name;
	}

	public WorkPreference getWorkPreference() {
		return workPreference;
	}

	public Role(boolean isSyncronize, String name, WorkPreference workPreference) {
		super();
		this.idRole = counter++;
		this.isSyncronize = isSyncronize;
		this.name = name;
		this.workPreference = workPreference;
		employees = new ArrayList<Employee>();
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public boolean setIsSyncronize(boolean isSyncronize) {
		this.isSyncronize = isSyncronize;
		return false;
	}

	@Override
	public void setPreference(WorkPreference workPreference) {
		this.workPreference = workPreference;
	}

	@Override
	public boolean getIsSyncronize() {
		return isSyncronize();
	}

	public void addEmployee(Employee employee) { // check the t
		employees.add(employee);
	}

	public double getRoleProfit(WorkPreference depWorkPreference, boolean depIsSyncronize) {
		double sumOfProfitFromRole = 0;
		WorkPreference preference = null;
		boolean sync = false;
		if (depIsSyncronize == true) {
			preference = depWorkPreference;
			sync = true;
		} else {
			preference = this.workPreference;
			sync = this.isSyncronize;
		}
		for (int i = 0; i < employees.size(); i++) {
			sumOfProfitFromRole += employees.get(i).employeeCalculateProfit(sync, preference);
		}
		return sumOfProfitFromRole;
	}

	@Override
	public String toString() {
		
	
		return" Role name: " + name +" ID :" + idRole +" is the Role syncronize: " + isSyncronize +" work Preference: " + workPreference + "\n";
	
	}

 	public String  printRRes() {
	return " name :" + name + "profit is :" +  getRoleProfit(workPreference, isSyncronize); }

}
