package helPet.entity;

import java.util.Date;

public class TherapyFrequency {
    private Long id;
    private Date from;
    private Date thru;
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

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getThru() {
        return thru;
    }

    public void setThru(Date thru) {
        this.thru = thru;
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
