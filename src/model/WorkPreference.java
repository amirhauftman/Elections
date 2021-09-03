package model;

public enum WorkPreference {

		//start_early,start_lately, regular, work_from_home 
	startEarly("Start Early"), startLate("Start Late"), noChange("Change Nothing"), workFromHome("Work From Home");

	private String displayName;

	WorkPreference(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return displayName;
	}

}

