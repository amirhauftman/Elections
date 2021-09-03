package model;

public class EmployeeBaseRate extends Employee{

	public static final int MAXHOURS = 160;
	private double basicSalary;
	
	public EmployeeBaseRate(int id, String name, WorkPreference currentPreference)throws BadInputException {
		super(id, name, currentPreference);
		// TODO Auto-generated constructor stub
	}
	
	public double calculateSalary() {
	 	double salasryPerMonth= basicSalary * MAXHOURS ;
	 	return salasryPerMonth;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	
	
}
