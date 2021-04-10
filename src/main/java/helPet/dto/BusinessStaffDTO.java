package helPet.dto;

import helPet.entity.BusinessRoleType;

public class BusinessStaffDTO extends UserDTO {
    private BusinessRoleType businessRoleType;

    public BusinessRoleType getBusinessRoleType() {
        return businessRoleType;
    }

    public void setBusinessRoleType(BusinessRoleType businessRoleType) {
        this.businessRoleType = businessRoleType;
    }
}
