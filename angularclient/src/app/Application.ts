import {User} from "./User";

export interface Application {
    id?: number;
    date: string;
    time: string;
    reason: number;
    reasonDescr: string;
    office: number;
    officeDescr: string;
    file: string;
    userIn: User;
    commentIn: string;
    userValid: string;
    commentValid: string;
    userApproved: string;
    commentApproved: string;
    status: number;
}
