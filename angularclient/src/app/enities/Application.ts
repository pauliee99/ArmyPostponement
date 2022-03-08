import {User} from "./User";
import {Office} from "./Office";

export interface Application {
    id?: number;
    date: string;
    time: string;
    reason: number;
    reasonDescr: string;
    office: Office;
    officeDescr: string;
    file: string;
    userIn: User;
    commentIn: number;
    userValid: string;
    commentValid: string;
    userApproved: string;
    commentApproved: string;
    status: number;
}
