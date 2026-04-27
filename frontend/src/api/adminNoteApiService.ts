import axiosInstance from "./axiosConfig";

export const getAllNotesAdmin = async () => {
  try {
    const response = await axiosInstance.get("/api/notes/allnotes");
    return response.data;
  } catch (error) {
    console.error("Error fetching notes admin:", error);
    throw error;
  }
};
