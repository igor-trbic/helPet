import { Timestamp } from 'rxjs';
import { EntityStatus } from './entitiyStatus.model';
import { Entity } from './entity.model';

export class Appointment extends Entity {
  id: number;
  date: Date;
  note: string;
  businessId: number;
  userId: number;
  petId: number;
  status: string;
}
