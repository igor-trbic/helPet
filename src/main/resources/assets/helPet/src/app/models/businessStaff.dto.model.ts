import { BusinessRoleType } from './businessRoleType.model';
import { EntityStatus } from './entitiyStatus.model';
import { UserDTO } from './user.dto.model';

export class BusinessStaffDTO extends UserDTO {
  businessRoleType: BusinessRoleType;
}