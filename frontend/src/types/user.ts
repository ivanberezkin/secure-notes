export interface User {
  id: string;
  username: string;
}

export interface UserCreateRequest {
  username: string;
  password: string;
}
