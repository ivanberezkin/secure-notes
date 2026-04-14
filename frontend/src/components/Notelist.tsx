import React, { useState } from "react";
import { CheckCircle2, ListFilter } from "lucide-react";

interface NotePreview {
  id: string;
  title: string;
  date: string;
  tags: string[];
  excerpt: string;
}

const notesData: NotePreview[] = [
  {
    id: "1",
    title: "Team Meeting Notes - Q4 Planning",
    date: "Aug 15",
    tags: ["Work"],
    excerpt:
      "Team Meeting Notes · sit amet, consectetur adipiscing elits sed up meeting a and for meeting our soritet...",
  },
  {
    id: "2",
    title: "Project Roadmap Q4",
    date: "Aug 14",
    tags: ["Design", "Meeting"],
    excerpt:
      "Project roadmap Q4 excerpts hight excerpt tromt meesiups teams, and plameed to ahove valuets and proma...",
  },
  {
    id: "3",
    title: "Vacation Itinerary",
    date: "Aug 12",
    tags: ["Personal", "Travel"],
    excerpt:
      "Vacation Itinerary dolor sit amet, consectetur adipiscing elit. Find the itinerary will echasey and line to a lia...",
  },
];

const NotesList: React.FC = () => {
  const [selectedId, setSelectedId] = useState("1");

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
        {notesData.map((note) => (
          <div
            key={note.id}
            onClick={() => setSelectedId(note.id)}
            className={`p-4 rounded-xl border cursor-pointer transition-all relative ${
              selectedId === note.id
                ? "bg-white border-blue-500 shadow-md ring-1 ring-blue-500"
                : "bg-white border-gray-200 hover:border-gray-300 shadow-sm"
            }`}
          >
            {/* Selection indicator icon */}
            {selectedId === note.id && (
              <CheckCircle2
                size={16}
                className="absolute top-4 right-4 text-blue-500"
              />
            )}

            <h3 className="font-bold text-gray-900 text-sm mb-1 pr-6 truncate">
              {note.title}
            </h3>

            <div className="flex gap-2 items-center mb-2">
              <span className="text-[10px] text-gray-400 font-medium uppercase">
                {note.date}
              </span>
              {note.tags.map((tag) => (
                <span
                  key={tag}
                  className="text-[10px] text-gray-500 bg-gray-100 px-1.5 py-0.5 rounded"
                >
                  #{tag}
                </span>
              ))}
            </div>

            <p className="text-xs text-gray-500 line-clamp-2 leading-relaxed">
              {note.excerpt}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default NotesList;
