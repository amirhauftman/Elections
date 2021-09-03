package model;

public class EmployeeHourly extends Employee{

	private double salaryPerHour;
	private double hoursInMonth;
	
	public double caculateSalary (int hoursInMonth, int salaryPerHour) {
		double salaryPerMonth =hoursInMonth *salaryPerHour ;
		return salaryPerMonth;
	}
	
	public EmployeeHourly(int id, String name, WorkPreference currentPreference) throws BadInputException {
		super(id, name, currentPreference);
		// TODO Auto-generated constructor stub
	}

	public double getSalaryPerHour() {
		return salaryPerHour;
	}

	public void setSalaryPerHour(double salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}

	public double getHoursInMonth() {
		return hoursInMonth;
	}

	public void setHoursInMonth(double hoursInMonth) {
		this.hoursInMonth = hoursInMonth;
	}

}
