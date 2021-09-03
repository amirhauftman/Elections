package model;

public class EmployeeSales extends Employee{

	private double salesAmount;
	public static final int MAXHOURS = 160;
	private double basicSalary;
	
	public EmployeeSales(int id, String name, WorkPreference currentPreference) throws BadInputException {
		super(id, name, currentPreference);
		// TODO Auto-generated constructor stub
	}

	public double calculateSalary() {
		double salasryPerMonth= (basicSalary * MAXHOURS) +(salesAmount*basicSalary)  ;
		return salasryPerMonth;
	}

	public double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(double salesAmount) {
		this.salesAmount = salesAmount;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

}
