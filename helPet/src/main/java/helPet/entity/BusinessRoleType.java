package helPet.entity;

import helPet.entity.util.EntityWithStatus;

public class BusinessRoleType extends EntityWithStatus {
    private Long id;
    private String businessRoleName;

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
}
