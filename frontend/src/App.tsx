import NoteEditor from "./components/NoteEditor";
import Notelist from "./components/Notelist";
import Sidebar from "./components/Sidebar";
import { TopBar } from "./components/TopBar";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { useAuth } from "./hooks/useAuth";
import Login from "./pages/Login";
import Register from "./pages/Register";
import type { Note } from "./types/note";
import { useState } from "react";

const ProtectedRoute = ({ children }: { children: React.ReactNode }) => {
  const { isAuthenticated } = useAuth();
  return isAuthenticated ? children : <Navigate to="/login" />;
};

function App() {
  const [selectedNote, setSelectedNote] = useState<Note | null>(null);
  const [isCreating, setIsCreating] = useState(false);
  const [refreshNotes, setRefreshNotes] = useState(0);

  const handleAction = () => {
    setSelectedNote(null);
    setIsCreating(false);
    setRefreshNotes((prev) => prev + 1);
  };

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route
          path="/"
          element={
            <ProtectedRoute>
              <div className="flex h-screen w-full bg-white overflow-hidden font-sans">
                <Sidebar />
                <div className="flex flex-col flex-1 h-full overflow-hidden">
                  <TopBar
                    onNewNote={() => {
                      setIsCreating(true);
                      setSelectedNote(null);
                    }}
                  />
                  <div className="flex flex-1 overflow-hidden">
                    <Notelist
                      onNoteSelect={(note) => {
                        setSelectedNote(note);
                        setIsCreating(false);
                      }}
                      refreshTrigger={refreshNotes}
                    />
                    <NoteEditor
                      note={selectedNote}
                      isCreating={isCreating}
                      onAction={handleAction}
                    />
                  </div>
                </div>
              </div>
            </ProtectedRoute>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
