package model;

public abstract class Employee implements Prefenceable{

	private WorkPreference workPreference;
	private boolean isSyncronize;
	private int id;
	private String name;
	private static final int PROFIT_PER_HOUR=10;
	private static final int DAILY_HOURS=8; 

	public WorkPreference getPreference() {
		return workPreference;
	}

	public Employee(int id, String name, WorkPreference workPreference)throws BadInputException {
		setId(id) ;
		this.name = name;
		this.workPreference = workPreference;
	}

	public Boolean setId(int id) throws BadInputException {
		if (id < 100000000)
			throw new BadInputException(1);
		if (id > 999999999)
			throw new BadInputException(2);
		this.id = id;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setPreference(WorkPreference workPreference) {
		this.workPreference = workPreference;
	}

	public String getName() {
		return name;
	}

	public double employeeCalculateProfit(boolean isSyncronize, WorkPreference workPreference) {
		double sumOfProfit=0;
		if (isSyncronize == true ) {// the department have to be syncronize so we can lose money
			if ( workPreference != this.workPreference) {
				return -1*0.2*PROFIT_PER_HOUR;
			}
			else {

				switch(this.workPreference) {
				case noChange:
					sumOfProfit=0;//no change  profit=profit           
					break;
				case startEarly:
					sumOfProfit=(0.2*PROFIT_PER_HOUR);  //profit*changehours*0.2,
					//profit*0.2
					break;
				case startLate:
					sumOfProfit=(0.2*PROFIT_PER_HOUR); //  profit*changehours*0.2	             
					break;             
				case workFromHome:
					sumOfProfit=PROFIT_PER_HOUR*DAILY_HOURS*0.1; //profit*0.1  
					break;	
				}
			}
		}
		else{
			switch(this.workPreference) {
			case noChange:
				if (workPreference == WorkPreference.noChange) {
				sumOfProfit=0;//no change  profit=profit 
				}
				else {
					sumOfProfit = 0.2*PROFIT_PER_HOUR;
				}
				break;
			case startEarly:
				sumOfProfit=(0.2*PROFIT_PER_HOUR);  
				//profit*changehours*0.2
				break;
			case startLate:
				sumOfProfit=(0.2*PROFIT_PER_HOUR);  
				//  profit*changehours*0.2
				break;             
			case workFromHome:
				if (workPreference == WorkPreference.workFromHome) {
				sumOfProfit=0; 
				}
				else {
					sumOfProfit=PROFIT_PER_HOUR*DAILY_HOURS*0.1;// all day profit*0.1
				}
				 
				break;	
			}
		}
		return sumOfProfit;
	}

	@Override
	public String toString() {
	
		return " Employee name: " + name +" ID : "+ id +" work Preference : "+ workPreference+ "\n" ;
	}
	public String printERes() {
		
		return  "  Employee name: " + name + ", profit is :" +employeeCalculateProfit(isSyncronize, workPreference) ;
	}
}
