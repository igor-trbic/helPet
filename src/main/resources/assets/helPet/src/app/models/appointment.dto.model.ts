import { Appointment } from './appointment.model';
import { Business } from './business.model';
import { Pet } from './pet.model';

export class AppointmentDTO extends Appointment {
  business: Business;
  pet: Pet;
}
