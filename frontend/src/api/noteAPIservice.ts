import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/notes";

export const getNotes = async () => {
  try {
    const response = await axios.get(API_BASE_URL);
    return response.data;
  } catch (error) {
    console.error("Error fetching notes:", error);
    throw error;
  }
};

export const getNoteById = async (noteId: string) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/${noteId}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching note with ID ${noteId}:`, error);
    throw error;
  }
};

export const createNote = async (noteToCreate: {
  title: string;
  content: string;
}) => {
  try {
    const response = await axios.post(API_BASE_URL, noteToCreate);
    return response.data;
  } catch (error) {
    console.error("Error creating note:", error);
    throw error;
  }
};

export const deleteNote = async (noteId: string) => {
  try {
    const response = await axios.delete(`${API_BASE_URL}/${noteId}`);
    return response.data;
  } catch (error) {
    console.error(`Error deleting note with ID ${noteId}:`, error);
    throw error;
  }
};
