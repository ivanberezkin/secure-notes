import NoteEditor from "./components/NoteEditor";
import Notelist from "./components/Notelist";
import Sidebar from "./components/Sidebar";
import { TopBar } from "./components/TopBar";

function App() {
  return (
    <div className="flex h-screen w-full bg-white overflow-hidden font-sans">
      <Sidebar />
      <div className="flex flex-col flex-1 h-full overflow-hidden">
        <TopBar />
        <div className="flex flex-1 overflow-hidden">
          <Notelist />
          <NoteEditor />
        </div>
      </div>
    </div>
  );
}

export default App;
