import { EntityStatus } from './entitiyStatus.model';
import { Entity } from './entity.model';

export class PetAttribute extends Entity {
  id: number;
  name: string;
  value: string;
  petId: number;
  status: EntityStatus;
}