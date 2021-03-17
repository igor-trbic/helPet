import { EntityStatus } from './entitiyStatus.model';
import { Entity } from './entity.model';

export class Business extends Entity {
  id: number;
  businessName: string;
  businessOwnerId: number;
  taxId: string;
  nationalId: string;
  status: EntityStatus;
}