export interface RoleScreenRequest {
    roleCode: string;
    screenCode: string;
    listAction: ActionRequest[];
}

export interface ActionRequest {
    actionCode: string;
    access: boolean;
}
