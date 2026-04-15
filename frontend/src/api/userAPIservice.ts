import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/users";

export const getUser = async (userId: number) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/${userId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching user:", error);
    throw error;
  }
};
