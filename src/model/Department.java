package model;

import java.util.ArrayList;

public class Department implements Prefenceable, Syncronizable {
	
	private static int counter=0;
	private int idDepartment;
	private String name;
	private ArrayList<Role> roles;
	private WorkPreference workPreference;
	private boolean isSyncronize;
	
	public Department(boolean isSyncronize, String name, WorkPreference prefernce)  {
		this.idDepartment = counter++;
		this.isSyncronize = isSyncronize;
		this.name = name;
		this.workPreference = prefernce;
		roles= new ArrayList<Role>();
	}
	
	public WorkPreference getPreference() {
		return workPreference;
	}
	
	public boolean getIsSyncronize() {
		return isSyncronize;
	}

	public boolean setIsSyncronize(boolean isSyncronize) {
		this.isSyncronize = isSyncronize;
		return true;
	}
	
	public void setIdDepartment(int idDepartment) {
		this.idDepartment = idDepartment;
	}

	public int getId() {
		return idDepartment;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void addRole(Role role) {
		roles.add(role);
	}
	
	public ArrayList<Role> getRoles() {
		return roles;
	}
	
	
	public void setRoles(ArrayList<Role> roles) {
		this.roles = roles;
	}

	@Override
	public void setPreference(WorkPreference workPreference) {
		this.workPreference=workPreference;
		
	}
	
	public double getDepartmentProfit() {
		double sumOfProfitFromDepartment=0;
		for (int i = 0; i < roles.size(); i++) {
			sumOfProfitFromDepartment+=roles.get(i).getRoleProfit(workPreference, isSyncronize);
		}
		return sumOfProfitFromDepartment;
	}

	@Override
	public String toString() {
		/*StringBuffer sb =new StringBuffer();
		sb.append("Department name: " + name );
		sb.append(" IjdfjfgkdjjdgkdD :"+ idDepartment);
		sb.append(" is the department syncronize : "+ isSyncronize );
		sb.append(" work Preference : "+ workPreference+ "\n");
		return sb.toString();
		*/
		
		return "Department name: " + name  +" ,ID Department is : " + idDepartment+  ", workPreference is :"
				+workPreference +" is the department syncronize : "+ isSyncronize + "\n";
				

		}
	
	
	public String printDRes() {
		
		return  "Department name: " +",The profit is :" + getDepartmentProfit() + "\n" ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
