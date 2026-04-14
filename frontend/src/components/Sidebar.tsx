import React, { useState } from "react";
import {
  StickyNote,
  Star,
  Trash2,
  Plus,
  Folder,
  Hash,
  ChevronDown,
} from "lucide-react";

interface NavItem {
  id: string;
  label: string;
  icon: React.ReactNode;
  count?: number;
}

const Sidebar: React.FC = () => {
  const [activeTab, setActiveTab] = useState("all-notes");

  const quickAccess: NavItem[] = [
    { id: "all-notes", label: "All Notes", icon: <StickyNote size={18} /> },
    { id: "favorites", label: "Favorites", icon: <Star size={18} /> },
    { id: "trash", label: "Trash", icon: <Trash2 size={18} /> },
  ];

  const notebooks = [
    { id: "work", label: "Work", count: 8 },
    { id: "personal", label: "Personal", count: 15 },
    { id: "projects", label: "Projects", count: 4 },
  ];

  return (
    <aside className="w-64 h-screen bg-[#1e2327] text-gray-300 flex flex-col p-4 border-r border-gray-700">
      {/* Profile Section */}
      <div className="flex items-center gap-3 mb-8 p-2 hover:bg-gray-800 rounded-lg cursor-pointer transition-colors">
        <div className="w-10 h-10 rounded-full bg-blue-500 flex items-center justify-center text-white font-bold">
          AJ
        </div>
        <div className="flex-1">
          <p className="text-sm font-semibold text-white">Alex J.</p>
          <p className="text-xs text-gray-400">Username</p>
        </div>
        <ChevronDown size={14} />
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

      {/* Notebooks */}
      <div className="mb-8">
        <div className="flex items-center justify-between px-2 mb-2">
          <p className="text-xs font-bold text-gray-500 uppercase tracking-wider">
            Notebooks
          </p>
          <Plus size={14} className="cursor-pointer hover:text-white" />
        </div>
        {notebooks.map((book) => (
          <div
            key={book.id}
            className="flex items-center justify-between px-3 py-2 text-sm hover:bg-gray-800 rounded-md cursor-pointer group"
          >
            <div className="flex items-center gap-3">
              <Folder
                size={18}
                className="text-gray-500 group-hover:text-blue-400"
              />
              <span>{book.label}</span>
            </div>
            <span className="text-xs text-gray-500">({book.count})</span>
          </div>
        ))}
      </div>

      {/* Tags */}
      <div>
        <div className="flex items-center justify-between px-2 mb-2">
          <p className="text-xs font-bold text-gray-500 uppercase tracking-wider">
            Tags
          </p>
        </div>
        <div className="flex flex-wrap gap-2 px-2">
          {["Work", "Meeting", "Travel", "Design"].map((tag) => (
            <span
              key={tag}
              className="flex items-center gap-1 px-2 py-1 bg-gray-800 hover:bg-gray-700 rounded text-xs cursor-pointer border border-transparent hover:border-gray-600"
            >
              <Hash size={10} />
              {tag}
            </span>
          ))}
        </div>
      </div>
    </aside>
  );
};

export default Sidebar;
