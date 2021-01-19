import { Role } from './ms001002-role-entity';

export interface User {
    userID: number;
    username: string;
    password: string;
    role: Role;
}
