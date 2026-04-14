import Notelist from "./components/Notelist";
import Sidebar from "./components/Sidebar";

function App() {
  return (
    <div className="flex h-screen w-full bg-white overflow-hidden">
      <Sidebar />
      <Notelist />
    </div>
  );
}

export default App;
