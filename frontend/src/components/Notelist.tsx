import React, { useEffect, useState } from "react";
import { ListFilter } from "lucide-react";
import type { Note } from "../types/note";
import { getUserNotes } from "../api/noteAPIservice";
import { getAllNotesAdmin } from "../api/adminNoteApiService";

interface Props {
  onNoteSelect: (note: Note) => void;
  refreshTrigger: number; // Optional prop to trigger refresh when notes are updated
  isAdminMode: boolean;
}

const Notelist: React.FC<Props> = ({
  onNoteSelect,
  refreshTrigger,
  isAdminMode,
}) => {
  const [selectedNote, setSelectedNote] = useState<number | null>(null);
  const [notes, setNotes] = useState<Note[]>([]);

  useEffect(() => {
    const fetchNotes = async () => {
      try {
        const notes = isAdminMode
          ? await getAllNotesAdmin()
          : await getUserNotes();
        setSelectedNote(null);
        setNotes(notes);
      } catch {
        console.error("Failed to fetch notes");
      }
    };

    fetchNotes();
  }, [refreshTrigger, isAdminMode]);

  return (
    <div className="w-120 h-full border-r border-gray-200 bg-gray-50 flex flex-col">
      {/* Header */}
      <div className="p-4 flex items-center justify-between border-b border-gray-200 bg-white">
        <h2 className="font-bold text-gray-800">Notes List</h2>
        <button className="p-1 hover:bg-gray-100 rounded text-gray-500">
          <ListFilter size={18} />
        </button>
      </div>

      {/* List Container */}
      <div className="flex-1 overflow-y-auto p-3 space-y-3">
        {notes.map((note) => (
          <div
            key={note.id}
            onClick={() => {
              setSelectedNote(note.id);
              onNoteSelect(note);
            }}
            className={`p-4 rounded-xl border cursor-pointer transition-all relative ${
              selectedNote === note.id
                ? "bg-white border-blue-500 shadow-md ring-1 ring-blue-500"
                : "bg-white border-gray-200 hover:border-gray-300 shadow-sm"
            }`}
          >
            {/* Title + Star */}
            <div className="flex items-center justify-between mb-1">
              <h3 className="font-bold text-gray-900 text-sm truncate pr-2">
                {note.title}
              </h3>
            </div>

            {/* Created at */}
            <p className="text-[10px] text-gray-400 font-medium mb-2">
              {new Date(note.createdAt).toLocaleDateString("en-US", {
                month: "short",
                day: "numeric",
                year: "numeric",
              })}
            </p>

            {/* User */}
            <p className="text-[10px] text-gray-400 font-medium mb-2">
              {note.username}
            </p>
            {/* Content preview */}
            <p className="text-xs text-gray-500 line-clamp-2 leading-relaxed">
              {note.content || "No content..."}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Notelist;
