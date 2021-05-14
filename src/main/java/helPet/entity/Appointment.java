package helPet.entity;

import helPet.entity.util.EntityWithStatus;

import java.sql.Timestamp;

public class Appointment extends EntityWithStatus {
    private Long id;
    private Timestamp date;
    private String note;
    private Long businessId;
    private Long userId;
    private Long petId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp timestamp) {
        this.date = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }
}
