import axiosInstance from "./axiosConfig";

export const getCurrentUser = async () => {
  try {
    const response = await axiosInstance.get("/api/users/me");
    return response.data;
  } catch (error) {
    console.error("Error fetching current user:", error);
    throw error;
  }
};

export const loginUser = async (username: string, password: string) => {
  try {
    const response = await axiosInstance.post("/login", {
      username,
      password,
    });
    return response.data;
  } catch (error) {
    console.error("Error logging in:", error);
    throw error;
  }
};

export const logoutUser = async () => {
  try {
    const response = await axiosInstance.post("/logout");
    return response.data;
  } catch (error) {
    console.error("Error logging out:", error);
    throw error;
  }
};

export const getCsrf = async () => {
  await axiosInstance.get("/csrf", {
    withCredentials: true,
  });
};
