package helPet.entity;

import helPet.entity.util.EntityWithStatus;

import java.util.Date;

public class TherapyFrequency extends EntityWithStatus {
    private Long id;
    private Date fromDate;
    private Date thruDate;
    private String description;
    private Integer times;
    private String timesPer;
    private Long therapyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getThruDate() {
        return thruDate;
    }

    public void setThruDate(Date thruDate) {
        this.thruDate = thruDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getTimesPer() {
        return timesPer;
    }

    public void setTimesPer(String timesPer) {
        this.timesPer = timesPer;
    }

    public Long getTherapyId() {
        return therapyId;
    }

    public void setTherapyId(Long therapyId) {
        this.therapyId = therapyId;
    }
}
