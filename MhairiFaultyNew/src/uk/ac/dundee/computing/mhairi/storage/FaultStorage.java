package uk.ac.dundee.computing.mhairi.storage;

public class FaultStorage {
	
	private int FaultID;
	private String Description;
	private int ReportedBy;
	private String Severity;
	private String Date;

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getReportedBy() {
		return ReportedBy;
	}

	public void setReportedBy(int reportedBy) {
		ReportedBy = reportedBy;
	}

	public String getSeverity() {
		return Severity;
	}

	public void setSeverity(String severity) {
		Severity = severity;
	}

	public int getFaultID() {
		return FaultID;
	}

	public void setFaultID(int faultID) {
		FaultID = faultID;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}
}
