package helPet.dto;

import helPet.entity.Appointment;
import helPet.entity.Business;
import helPet.entity.Pet;

public class AppointmentDTO extends Appointment {
    private Business business;
    private Pet pet;

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
