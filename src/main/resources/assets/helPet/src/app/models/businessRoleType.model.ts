import { EntityStatus } from './entitiyStatus.model';
import { Entity } from './entity.model';

export class BusinessRoleType extends Entity {
  id: number;
  businessRoleName: string;
  businessId: number;
  status: EntityStatus;
}