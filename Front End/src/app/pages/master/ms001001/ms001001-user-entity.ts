import { Role } from './ms001001-role-entity';

export interface User {
    userID: number;
    username: string;
    password: string;
    role: Role;
    employeeName: string;
}
