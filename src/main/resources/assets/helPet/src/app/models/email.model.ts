import { EntityStatus } from './entitiyStatus.model';
import { Entity } from './entity.model';

export class Email extends Entity {
  id: number;
  emailAddress: string;
  emailType: string;
  isPrimary: boolean = false;
  status: EntityStatus;
}