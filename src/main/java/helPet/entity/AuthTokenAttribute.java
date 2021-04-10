package helPet.entity;

import java.util.Date;

public class AuthTokenAttribute {

    private String attrName;
    private String attrValue;
    private Date createdOn;
    private String createdBy;
    private String tokenUserAuthToken;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getTokenUserAuthToken() {
        return tokenUserAuthToken;
    }

    public void setTokenUserAuthToken(String tokenUserAuthToken) {
        this.tokenUserAuthToken = tokenUserAuthToken;
    }
}
