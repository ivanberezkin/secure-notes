export interface Note {
  id: number;
  title: string;
  content: string;
  username: string;
  createdAt: string;
  updatedAt: string;
  favorite: boolean;
}

export interface NoteCreateRequest {
  title: string;
  content: string;
  favorite: boolean;
}
