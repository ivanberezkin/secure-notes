import axios from "axios";

const API_BASE_URL = `${import.meta.env.VITE_API_BASE_URL}/api/users`;

export const getCurrentUser = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/me`, {
      withCredentials: true,
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching current user:", error);
    throw error;
  }
};

export const loginUser = async (username: string, password: string) => {
  try {
    const response = await axios.post(
      `${API_BASE_URL}/login`,
      {
        username,
        password,
      },
      { withCredentials: true },
    );
    return response.data;
  } catch (error) {
    console.error("Error logging in:", error);
    throw error;
  }
};
