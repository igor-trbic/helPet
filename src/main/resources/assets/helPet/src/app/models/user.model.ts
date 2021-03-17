import { Entity } from './entity.model';

export class User extends Entity {
  username: string;
  firstName: string;
  lastName: string;
  password: string;
}