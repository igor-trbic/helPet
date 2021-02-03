import { EntityStatus } from './entitiyStatus.model';
import { Entity } from './entity.model';

export class Address extends Entity {
  id: number;
  streetName: string;
  houseNumber: string;
  postalCode: string;
  addressType: string;
  status: EntityStatus;
}