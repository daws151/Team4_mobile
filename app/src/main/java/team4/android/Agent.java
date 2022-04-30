package team4.android;

import java.io.Serializable;


/**
 * The persistent class for the agents database table.
 * 
 */
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;

	private int agentId;

	private int agencyId;

	private String agtBusPhone;

	private String agtEmail;

	private String agtFirstName;

	private String agtLastName;

	private String agtMiddleInitial;

	private String agtPosition;

	private String password;

	private String userid;

	public Agent() {
	}

	public Agent(int agentId, String agtFirstName, String agtMiddleInitial, String agtLastName,
                 String agtBusPhone, String agtEmail, String agtPosition, int agencyId, String userid,
                 String password) {
		this.agentId = agentId;
		this.agencyId = agencyId;
		this.agtBusPhone = agtBusPhone;
		this.agtEmail = agtEmail;
		this.agtFirstName = agtFirstName;
		this.agtLastName = agtLastName;
		this.agtMiddleInitial = agtMiddleInitial;
		this.agtPosition = agtPosition;
		this.password = password;
		this.userid = userid;
	}

	public int getAgentId() {
		return this.agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getAgencyId() {
		return this.agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgtBusPhone() {
		return this.agtBusPhone;
	}

	public void setAgtBusPhone(String agtBusPhone) {
		this.agtBusPhone = agtBusPhone;
	}

	public String getAgtEmail() {
		return this.agtEmail;
	}

	public void setAgtEmail(String agtEmail) {
		this.agtEmail = agtEmail;
	}

	public String getAgtFirstName() {
		return this.agtFirstName;
	}

	public void setAgtFirstName(String agtFirstName) {
		this.agtFirstName = agtFirstName;
	}

	public String getAgtLastName() {
		return this.agtLastName;
	}

	public void setAgtLastName(String agtLastName) {
		this.agtLastName = agtLastName;
	}

	public String getAgtMiddleInitial() {
		return this.agtMiddleInitial;
	}

	public void setAgtMiddleInitial(String agtMiddleInitial) {
		this.agtMiddleInitial = agtMiddleInitial;
	}

	public String getAgtPosition() {
		return this.agtPosition;
	}

	public void setAgtPosition(String agtPosition) {
		this.agtPosition = agtPosition;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return agtFirstName + ' ' + agtLastName;
	}
}