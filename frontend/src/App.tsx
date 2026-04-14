import NoteEditor from "./components/NoteEditor";
import Notelist from "./components/Notelist";
import Sidebar from "./components/Sidebar";

function App() {
  return (
    <div className="flex h-screen w-full bg-white overflow-hidden font-sans">
      <Sidebar />
      <Notelist />
      <NoteEditor />
    </div>
  );
}

export default App;
