export enum Role {
  TUTOR,
  STUDENT
}

export class UserClientInfo {
  name: string;
  surname: string;
  username: string;
  role: Role;
}
