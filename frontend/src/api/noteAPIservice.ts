import type { NoteCreateRequest } from "../types/note";
import axiosInstance from "./axiosConfig";

export const getUserNotes = async () => {
  try {
    const response = await axiosInstance.get("/api/notes/my");
    return response.data;
  } catch (error) {
    console.error("Error fetching notes:", error);
    throw error;
  }
};

export const getNoteById = async (noteId: number) => {
  try {
    const response = await axiosInstance.get(`/api/notes/${noteId}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching note with ID ${noteId}:`, error);
    throw error;
  }
};

export const createNote = async (noteToCreate: NoteCreateRequest) => {
  try {
    const response = await axiosInstance.post("/api/notes", noteToCreate);
    return response.data;
  } catch (error) {
    console.error("Error creating note:", error);
    throw error;
  }
};

export const deleteNote = async (noteId: number) => {
  try {
    const response = await axiosInstance.delete(`/api/notes/${noteId}`);
    return response.data;
  } catch (error) {
    console.error(`Error deleting note with ID ${noteId}:`, error);
    throw error;
  }
};
