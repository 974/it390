package jdodb;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class ReportEntry {
	@ForeignKey
	@Persistent
	private String userID;
	@ForeignKey
	@Persistent
	private String combineStoreAndItemID;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String reportID;
	@Persistent
	private double reportedPrice;
	
	public ReportEntry(String combinedKey){
		combineStoreAndItemID = combinedKey;
		userID = null;
		reportedPrice = 0;
	}
	public ReportEntry(String combinedKey, String userID, double reportedPrice){
		this.combineStoreAndItemID = combinedKey;
		this.userID = userID;
		this.reportedPrice = reportedPrice;
	}
	public String getUserID(){
		return userID;
	}
	public String getCombineStoreAndItemID(){
		return combineStoreAndItemID;
	}
	public String getReportID(){return reportID;}
	public double getReportedPrice(){return reportedPrice;}
	public void setUserID(String id){
		this.userID = id;
	}
	public void setCombineStoreAndItemID(String id){
		this.combineStoreAndItemID = id;
	}
	public void setReportedPrice(double price){
		reportedPrice = price;
	}
}

