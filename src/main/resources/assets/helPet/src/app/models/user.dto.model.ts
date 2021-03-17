import { EntityStatus } from './entitiyStatus.model';
import { User } from './user.model';

export class UserDTO extends User {
  businessId: number;
  businessName: string;
  token: string;
}