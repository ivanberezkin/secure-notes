import React, { useEffect } from "react";
import { Save, Trash2, StickyNote } from "lucide-react";
import type { Note } from "../types/note";
import { createNote, deleteNote, updateNote } from "../api/noteAPIservice";

interface NoteEditorProps {
  note: Note | null;
  isCreating?: boolean;
  onAction: () => void;
}

const NoteEditor: React.FC<NoteEditorProps> = ({
  note,
  isCreating,
  onAction,
}) => {
  const [title, setTitle] = React.useState("");
  const [content, setContent] = React.useState("");
  const [favorite, setFavorite] = React.useState(note?.favorite || false);
  const [error, setError] = React.useState<string | null>(null);

  useEffect(() => {
    if (note) {
      setTitle(note.title ?? "");
      setContent(note.content ?? "");
      setFavorite(note.favorite);
    } else if (isCreating) {
      setTitle("");
      setContent("");
      setFavorite(false);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [note?.id, isCreating]);

  const handleSave = async () => {
    setError(null);
    try {
      if (isCreating) {
        await createNote({ title, content, favorite });
        onAction();
      } else if (note) {
        await updateNote(note.id, {
          title,
          content,
          favorite,
        }); // Implement updateNote to call API
        onAction();
      }
    } catch {
      setError("Failed to save note. Please try again.");
    }
  };

  const handleDelete = async () => {
    try {
      await deleteNote(note!.id);
      onAction();
    } catch {
      setError("Failed to delete note. Please try again.");
    }
  };

  if (!note && !isCreating) {
    return (
      <div className="flex-1 flex items-center justify-center bg-white">
        <div className="text-center text-gray-400">
          <StickyNote size={48} className="mx-auto mb-4 opacity-30" />
          <p className="text-lg font-medium">Select a note to view</p>
          <p className="text-sm">or create a new one</p>
        </div>
      </div>
    );
  }
  return (
    <div className="flex-1 flex flex-col h-full bg-white">
      <main className="flex-1 overflow-y-auto p-12 max-w-4xl">
        {error && (
          <div className="bg-red-50 text-red-600 px-4 py-3 rounded-lg mb-4 text-sm">
            {error}
          </div>
        )}

        {/* Title input */}
        <input
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Note title..."
          className="w-full text-4xl font-bold text-gray-900 mb-4 outline-none border-none placeholder-gray-300"
        />

        {/* Date */}
        {note && (
          <p className="text-sm text-gray-400 mb-8">
            {new Date(note.createdAt).toLocaleDateString("en-US", {
              month: "long",
              day: "numeric",
              year: "numeric",
            })}
          </p>
        )}

        {/* Toolbar */}
        <div className="flex items-center gap-1 mb-8 border-b border-gray-100 pb-4 text-gray-600">
          <button
            onClick={handleSave}
            className="flex items-center gap-2 px-3 py-1.5 bg-blue-600 hover:bg-blue-700 text-white rounded-lg text-sm font-medium transition-colors"
          >
            <Save size={16} />
            {isCreating ? "Create" : "Save"}
          </button>

          {!isCreating && (
            <button
              onClick={handleDelete}
              type="button"
              aria-label="Delete Note"
              className="p-2 hover:bg-gray-100 rounded transition-colors text-red-400 hover:text-red-600 ml-auto"
            >
              <Trash2 size={20} />
            </button>
          )}
        </div>

        {/* Content textarea */}
        <textarea
          value={content}
          onChange={(e) => setContent(e.target.value)}
          placeholder="Start writing your note..."
          className="w-full h-96 text-gray-700 outline-none resize-none placeholder-gray-300 leading-relaxed"
        />
      </main>
    </div>
  );
};

export default NoteEditor;
