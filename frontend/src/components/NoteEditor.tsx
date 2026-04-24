import React from "react";
import {
  Bold,
  Italic,
  Underline,
  List,
  ListOrdered,
  Paperclip,
  Link,
  Trash2,
} from "lucide-react";
import type { Note } from "../types/note";

interface NoteEditorProps {
  note: Note | null;
}

const NoteEditor: React.FC<NoteEditorProps> = ({ note }) => {
  return (
    <div className="flex-1 flex flex-col h-full bg-white">
      {/* Editor Area */}
      <main className="flex-1 overflow-y-auto p-12 max-w-4xl">
        {/* Note Metadata */}
        <div className="mb-8">
          <h1 className="text-4xl font-bold text-gray-900 mb-4">
            {note?.title || "Untitled Note"}
          </h1>
          <div className="flex items-center gap-4 text-sm text-gray-500">
            <span>
              Created{" "}
              {note?.createdAt
                ? new Date(note.createdAt).toLocaleString()
                : "Unknown"}
            </span>
          </div>
          <div className="flex items-center gap-4 text-sm text-gray-500">
            <span>
              Updated{" "}
              {note?.updatedAt
                ? new Date(note.updatedAt).toLocaleString()
                : "Unknown"}
            </span>
          </div>
        </div>

        {/* Toolbar */}
        <div className="flex items-center gap-1 mb-8 border-b border-gray-100 pb-4 text-gray-600">
          <button
            type="button"
            aria-label="Bold"
            className="p-2 hover:bg-gray-100 rounded transition-colors"
          >
            <Bold size={20} />
          </button>
          <button
            type="button"
            aria-label="Italic"
            className="p-2 hover:bg-gray-100 rounded transition-colors"
          >
            <Italic size={20} />
          </button>
          <button
            type="button"
            aria-label="Underline"
            className="p-2 hover:bg-gray-100 rounded transition-colors underline"
          >
            <Underline size={20} />
          </button>
          <div className="w-px h-6 bg-gray-200 mx-2" />
          <button
            type="button"
            aria-label="List"
            className="p-2 hover:bg-gray-100 rounded transition-colors"
          >
            <List size={20} />
          </button>
          <button
            type="button"
            aria-label="Ordered List"
            className="p-2 hover:bg-gray-100 rounded transition-colors"
          >
            <ListOrdered size={20} />
          </button>
          <div className="w-px h-6 bg-gray-200 mx-2" />
          <button
            type="button"
            aria-label="Attach File"
            className="p-2 hover:bg-gray-100 rounded transition-colors"
          >
            <Paperclip size={20} />
          </button>
          <button
            type="button"
            aria-label="Insert Link"
            className="p-2 hover:bg-gray-100 rounded transition-colors"
          >
            <Link size={20} />
          </button>
          <button
            type="button"
            aria-label="Delete Note"
            className="p-2 hover:bg-gray-100 rounded transition-colors text-red-400 hover:text-red-600 ml-auto"
          >
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
        </article>
      </main>
    </div>
  );
};

export default NoteEditor;
