import React, { useState } from "react";
import { StickyNote, Star, LogOut } from "lucide-react";
import { logoutUser } from "../api/userAPIservice";
import { useAuth } from "../hooks/useAuth";
import { useNavigate } from "react-router";

interface NavItem {
  id: string;
  label: string;
  icon: React.ReactNode;
  count?: number;
}

const Sidebar: React.FC = () => {
  const [activeTab, setActiveTab] = useState("all-notes");
  const { logout } = useAuth();
  const navigate = useNavigate();
  const { username, role } = useAuth();

  const quickAccess: NavItem[] = [
    { id: "all-notes", label: "All Notes", icon: <StickyNote size={18} /> },
    { id: "favorites", label: "Favorites", icon: <Star size={18} /> },
  ];

  const handleLogout = async () => {
    try {
      await logoutUser();
    } catch (error) {
      console.error("Error during logout:", error);
    } finally {
      logout();
      navigate("/login");
    }
  };

  return (
    <aside className="w-64 h-screen bg-[#1e2327] text-gray-300 flex flex-col p-4 border-r border-gray-700">
      {/* Profile Section */}
      <div className="flex items-center gap-3 mb-8 p-2 hover:bg-gray-800 rounded-lg cursor-pointer transition-colors">
        <div className="w-10 h-10 rounded-full bg-blue-500 flex items-center justify-center text-white font-bold">
          {username?.charAt(0).toUpperCase() || "U"}
        </div>
        <div className="flex-1">
          <p className="text-sm font-semibold text-white">{username}</p>
          <p className="text-xs text-gray-400">
            {role === "admin" ? "Administrator" : "User"}
          </p>
        </div>
      </div>

      {/* Quick Access */}
      <nav className="mb-8">
        <p className="text-xs font-bold text-gray-500 uppercase tracking-wider mb-2 px-2">
          Quick Access
        </p>
        {quickAccess.map((item) => (
          <button
            key={item.id}
            onClick={() => setActiveTab(item.id)}
            className={`w-full flex items-center gap-3 px-3 py-2 rounded-md text-sm transition-all ${
              activeTab === item.id
                ? "bg-blue-600 text-white"
                : "hover:bg-gray-800"
            }`}
          >
            {item.icon}
            {item.label}
          </button>
        ))}
      </nav>

      <div className="mt-auto">
        <button
          onClick={handleLogout}
          className="w-full flex items-center gap-3 px-3 py-2 rounded-md text-sm text-red-400 hover:bg-gray-800 hover:text-red-300 transition-all"
        >
          <LogOut size={40} />
          Logout
        </button>
      </div>
    </aside>
  );
};

export default Sidebar;
