import { EntityStatus } from './entitiyStatus.model';
import { Entity } from './entity.model';

export class Phone extends Entity {
  id: number;
  phoneNumber: string;
  phoneType: string;
  status: EntityStatus;
}