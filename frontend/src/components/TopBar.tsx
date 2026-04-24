import React from "react";
import { Search, Settings, Plus } from "lucide-react";

interface TopBarProps {
  onNewNote: () => void;
}

export const TopBar: React.FC<TopBarProps> = ({ onNewNote }) => {
  return (
    <header className="h-16 border-b border-gray-100 flex items-center justify-between px-8 bg-white">
      <h2 className="text-2xl font-bold text-gray-800">Projects</h2>

      <div className="flex items-center gap-6">
        {/* Search Bar */}
        <div className="relative">
          <label htmlFor="topbar-search" className="sr-only">
            Search notes
          </label>
          <Search
            className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400"
            size={18}
          />
          <input
            id="topbar-search"
            type="text"
            placeholder="Search all notes..."
            className="pl-10 pr-4 py-2 bg-gray-50 border border-gray-200 rounded-lg text-sm w-64 focus:outline-none focus:ring-2 focus:ring-blue-500/20 focus:border-blue-500 transition-all"
          />
        </div>

        <button
          onClick={onNewNote}
          className="flex items-center gap-2 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg text-sm font-semibold transition-colors shadow-sm shadow-blue-200"
        >
          <Plus size={18} />
          New Note
        </button>

        <div className="flex items-center gap-3 text-gray-400 border-l pl-6 border-gray-200">
          <button
            type="button"
            aria-label="Settings"
            className="p-1 rounded hover:text-gray-600 hover:bg-gray-100"
          >
            <Settings size={20} />
          </button>
        </div>
      </div>
    </header>
  );
};
