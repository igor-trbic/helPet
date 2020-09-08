package helPet.entity.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Entity {
    private Date createdOn;
    private String createdBy;
    private Date updatedOn;
    private String updatedBy;

    public Date getCreatedOn() {
        return createdOn;
    }

    @JsonIgnore
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    @JsonIgnore
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    @JsonIgnore
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    @JsonIgnore
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
