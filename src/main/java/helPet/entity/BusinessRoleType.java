package helPet.entity;

import helPet.entity.util.EntityWithStatus;

public class BusinessRoleType extends EntityWithStatus {
    private Long id;
    private String businessRoleName;
    private Long businessId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessRoleName() {
        return businessRoleName;
    }

    public void setBusinessRoleName(String businessRoleName) {
        this.businessRoleName = businessRoleName;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
}
