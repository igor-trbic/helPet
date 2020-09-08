package helPet.entity;

import helPet.entity.util.EntityWithStatus;

public class BusinessRole extends EntityWithStatus {
    private Long id;
    private Long businessStaffId;
    private Long businessRoleTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessStaffId() {
        return businessStaffId;
    }

    public void setBusinessStaffId(Long businessStaffId) {
        this.businessStaffId = businessStaffId;
    }

    public Long getBusinessRoleTypeId() {
        return businessRoleTypeId;
    }

    public void setBusinessRoleTypeId(Long businessRoleTypeId) {
        this.businessRoleTypeId = businessRoleTypeId;
    }
}
