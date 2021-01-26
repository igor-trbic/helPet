import { EntityStatus } from './entitiyStatus.model';
import { Entity } from './entity.model';

export class Pet extends Entity {
  id: number;
  name: string;
  dateOfBirth: Date;
  note: string;
  status: EntityStatus;
}