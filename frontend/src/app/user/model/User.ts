
export interface User {
  email: string;
  password: string;
  authorities: [{authority: string}];
}
