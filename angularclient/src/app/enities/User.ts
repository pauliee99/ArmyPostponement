import {Authorities} from "./Authorities";

export interface User {
  username: number;
  password: string;
  enabled: number;
  firstname: string;
  lastname: string;
  asm: number;
  office: number;
  authorities: Authorities;
}
