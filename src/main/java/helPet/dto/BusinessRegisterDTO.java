package helPet.dto;

import helPet.entity.Business;
import helPet.entity.User;

public class BusinessRegisterDTO {
    Business business;
    User user;

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
