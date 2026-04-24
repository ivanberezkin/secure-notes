import axios from "axios";
import type { NoteCreateRequest } from "../types/note";
import axiosInstance from "./axiosConfig";

const API_BASE_URL = `${import.meta.env.VITE_API_BASE_URL}/api/notes`;

export const getNotes = async () => {
  try {
    const response = await axiosInstance.get(API_BASE_URL);
    return response.data;
  } catch (error) {
    console.error("Error fetching notes:", error);
    throw error;
  }
};

export const getNoteById = async (noteId: number) => {
  try {
    const response = await axiosInstance.get(`${API_BASE_URL}/${noteId}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching note with ID ${noteId}:`, error);
    throw error;
  }
};

export const createNote = async (noteToCreate: NoteCreateRequest) => {
  try {
    const response = await axiosInstance.post(API_BASE_URL, noteToCreate);
    return response.data;
  } catch (error) {
    console.error("Error creating note:", error);
    throw error;
  }
};

export const deleteNote = async (noteId: number) => {
  try {
    const response = await axiosInstance.delete(`${API_BASE_URL}/${noteId}`);
    return response.data;
  } catch (error) {
    console.error(`Error deleting note with ID ${noteId}:`, error);
    throw error;
  }
};
