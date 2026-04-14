import React from "react";
import {
  Search,
  Bell,
  Settings,
  Plus,
  Bold,
  Italic,
  Underline,
  List,
  ListOrdered,
  Paperclip,
  Link,
  Trash2,
} from "lucide-react";

const NoteEditor: React.FC = () => {
  return (
    <div className="flex-1 flex flex-col h-full bg-white">
      {/* Top Utility Bar */}
      <header className="h-16 border-b border-gray-100 flex items-center justify-between px-8 bg-white">
        <h2 className="text-2xl font-bold text-gray-800">Projects</h2>

        <div className="flex items-center gap-6">
          {/* Search Bar */}
          <div className="relative">
            <Search
              className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400"
              size={18}
            />
            <input
              type="text"
              placeholder="Search all notes..."
              className="pl-10 pr-4 py-2 bg-gray-50 border border-gray-200 rounded-lg text-sm w-64 focus:outline-none focus:ring-2 focus:ring-blue-500/20 focus:border-blue-500 transition-all"
            />
          </div>

          <button className="flex items-center gap-2 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg text-sm font-semibold transition-colors shadow-sm shadow-blue-200">
            <Plus size={18} />
            New Note
          </button>

          <div className="flex items-center gap-3 text-gray-400 border-l pl-6 border-gray-200">
            <Bell size={20} className="cursor-pointer hover:text-gray-600" />
            <Settings
              size={20}
              className="cursor-pointer hover:text-gray-600"
            />
          </div>
        </div>
      </header>

      {/* Editor Area */}
      <main className="flex-1 overflow-y-auto p-12 max-w-4xl">
        {/* Note Metadata */}
        <div className="mb-8">
          <h1 className="text-4xl font-bold text-gray-900 mb-4">
            Team Meeting Notes - Q4 Planning
          </h1>
          <div className="flex items-center gap-4 text-sm text-gray-500">
            <span>Aug 15, 2023 | 2:10 PM</span>
            <div className="flex items-center gap-2">
              <span className="font-medium">Tags:</span>
              <span className="bg-gray-100 px-2 py-0.5 rounded text-gray-600">
                #Work
              </span>
              <span className="bg-gray-100 px-2 py-0.5 rounded text-gray-600">
                #Meeting
              </span>
            </div>
          </div>
        </div>

        {/* Toolbar */}
        <div className="flex items-center gap-1 mb-8 border-b border-gray-100 pb-4 text-gray-600">
          <button className="p-2 hover:bg-gray-100 rounded transition-colors">
            <Bold size={20} />
          </button>
          <button className="p-2 hover:bg-gray-100 rounded transition-colors">
            <Italic size={20} />
          </button>
          <button className="p-2 hover:bg-gray-100 rounded transition-colors underline">
            <Underline size={20} />
          </button>
          <div className="w-px h-6 bg-gray-200 mx-2" />
          <button className="p-2 hover:bg-gray-100 rounded transition-colors">
            <List size={20} />
          </button>
          <button className="p-2 hover:bg-gray-100 rounded transition-colors">
            <ListOrdered size={20} />
          </button>
          <div className="w-px h-6 bg-gray-200 mx-2" />
          <button className="p-2 hover:bg-gray-100 rounded transition-colors">
            <Paperclip size={20} />
          </button>
          <button className="p-2 hover:bg-gray-100 rounded transition-colors">
            <Link size={20} />
          </button>
          <button className="p-2 hover:bg-gray-100 rounded transition-colors text-red-400 hover:text-red-600 ml-auto">
            <Trash2 size={20} />
          </button>
        </div>

        {/* Content Placeholder */}
        <article className="prose prose-slate max-w-none">
          <h3 className="text-xl font-bold text-gray-800 mb-4">
            Bulleted lists
          </h3>
          <ul className="list-disc pl-5 space-y-2 text-gray-700 mb-8">
            <li>Predeveloping content updates and planning</li>
            <li>Prioritize learning attributes</li>
            <li>Identify new goals and automation needs</li>
          </ul>

          <h3 className="text-xl font-bold text-gray-800 mb-4">
            Identify new goals
          </h3>
          <div className="space-y-3">
            <label className="flex items-center gap-3 cursor-pointer">
              <input
                type="checkbox"
                className="w-5 h-5 rounded border-gray-300 text-blue-600 focus:ring-blue-500"
              />
              <span className="text-gray-700">Discuss budget updates</span>
            </label>
            <label className="flex items-center gap-3 cursor-pointer">
              <input
                type="checkbox"
                checked
                readOnly
                className="w-5 h-5 rounded border-gray-300 text-blue-600 focus:ring-blue-500"
              />
              <span className="text-gray-700 line-through decoration-gray-400">
                Identify new goals
              </span>
            </label>
          </div>
        </article>
      </main>
    </div>
  );
};

export default NoteEditor;
