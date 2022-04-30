package team4.android;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the agents database table.
 * 
 */
public class Package implements Serializable {
	private static final long serialVersionUID = 1L;

	private int packageId;

	private String pkgName;

	private Date pkgStartDate;

	private Date pkgEndDate;

	private String pkgDesc;

	private double pkgBasePrice;

	private double pkgAgencyCommission;

	public Package() {
	}

	public Package(int packageId, String pkgName, Date pkgStartDate, Date pkgEndDate, String pkgDesc, double pkgBasePrice, double pkgAgencyCommission) {
		this.packageId = packageId;
		this.pkgName = pkgName;
		this.pkgStartDate = pkgStartDate;
		this.pkgEndDate = pkgEndDate;
		this.pkgDesc = pkgDesc;
		this.pkgBasePrice = pkgBasePrice;
		this.pkgAgencyCommission = pkgAgencyCommission;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public Date getPkgStartDate() {
		return pkgStartDate;
	}

	public void setPkgStartDate(Date pkgStartDate) {
		this.pkgStartDate = pkgStartDate;
	}

	public Date getPkgEndDate() {
		return pkgEndDate;
	}

	public void setPkgEndDate(Date pkgEndDate) {
		this.pkgEndDate = pkgEndDate;
	}

	public String getPkgDesc() {
		return pkgDesc;
	}

	public void setPkgDesc(String pkgDesc) {
		this.pkgDesc = pkgDesc;
	}

	public double getPkgBasePrice() {
		return pkgBasePrice;
	}

	public void setPkgBasePrice(double pkgBasePrice) {
		this.pkgBasePrice = pkgBasePrice;
	}

	public double getPkgAgencyCommission() {
		return pkgAgencyCommission;
	}

	public void setPkgAgencyCommission(double pkgAgencyCommission) {
		this.pkgAgencyCommission = pkgAgencyCommission;
	}

	@Override
	public String toString() {
		return pkgName;
	}
}