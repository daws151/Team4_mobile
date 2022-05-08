package team4.android;

import java.io.Serializable;

/*** Author: William Rust, Harbir Singh, Brett Dawson
 *   Date: May 07, 2022
 *   Comments: Agent class from the Travel Experts database. Has columns added for User ID and Password.
 ****/

public class Agent implements Serializable {

    private static final long serialVersionUID = 1L;

    private int AgentId;

    private String agtFirstName;

    private String agtMiddleInitial;

    private String agtLastName;

    private String agtBusPhone;

    private String agtEmail;

    private String agtPosition;

    private int agencyId;

    private String agtUserId;

    private String agtPassword;

    public Agent(int agentId, String agtFirstName, String agtMiddleInitial, String agtLastName, String agtBusPhone, String agtEmail, String agtPosition, int agencyId, String agtUserId, String agtPassword) {
        AgentId = agentId;
        this.agtFirstName = agtFirstName;
        this.agtMiddleInitial = agtMiddleInitial;
        this.agtLastName = agtLastName;
        this.agtBusPhone = agtBusPhone;
        this.agtEmail = agtEmail;
        this.agtPosition = agtPosition;
        this.agencyId = agencyId;
        this.agtUserId = agtUserId;
        this.agtPassword = agtPassword;
    }

    public int getAgentId() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        AgentId = agentId;
    }

    public String getAgtFirstName() {
        return agtFirstName;
    }

    public void setAgtFirstName(String agtFirstName) {
        this.agtFirstName = agtFirstName;
    }

    public String getAgtMiddleInitial() {
        return agtMiddleInitial;
    }

    public void setAgtMiddleInitial(String agtMiddleInitial) {
        this.agtMiddleInitial = agtMiddleInitial;
    }

    public String getAgtLastName() {
        return agtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        this.agtLastName = agtLastName;
    }

    public String getAgtBusPhone() {
        return agtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        this.agtBusPhone = agtBusPhone;
    }

    public String getAgtEmail() {
        return agtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        this.agtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return agtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        this.agtPosition = agtPosition;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgtUserId() {
        return agtUserId;
    }

    public void setAgtUserId(String agtUserId) {
        this.agtUserId = agtUserId;
    }

    public String getAgtPassword() {
        return agtPassword;
    }

    public void setAgtPassword(String agtPassword) {
        this.agtPassword = agtPassword;
    }

    @Override
    public String toString() {
        return agtFirstName + " " + agtLastName;
    }
}
