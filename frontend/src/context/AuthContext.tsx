import { createContext, useEffect, useState, type ReactNode } from "react";
import { getCsrf } from "../api/userAPIservice";
import axios from "axios";

interface AuthContextType {
  isAuthenticated: boolean;
  isAuthLoading: boolean;
  username: string | null;
  role: string | null;
  login: (username: string, role: string) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | null>(null);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState<string | null>(null);
  const [role, setRole] = useState<string | null>(null);
  const [isAuthLoading, setIsAuthLoading] = useState(true);

  useEffect(() => {
    const checkAuth = async () => {
      try {
        await getCsrf();
        const response = await axios.get(
          `${import.meta.env.VITE_API_BASE_URL}/api/users/me`,
          {
            withCredentials: true,
          },
        );
        setIsAuthenticated(true);
        setUsername(response.data.username);
        setRole(response.data.role);
      } catch (error) {
        if (
          axios.isAxiosError(error) &&
          (error.response?.status === 401 || error.response?.status === 403)
        ) {
          setIsAuthenticated(false);
          setUsername(null);
          setRole(null);
        } else {
          console.error("Error checking authentication:", error);
        }
      } finally {
        setIsAuthLoading(false);
      }
    };
    checkAuth();
  }, []);

  const login = (username: string, role: string) => {
    setIsAuthenticated(true);
    setUsername(username);
    setRole(role);
  };

  const logout = () => {
    setIsAuthenticated(false);
    setUsername(null);
    setRole(null);
  };

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, isAuthLoading, username, role, login, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
