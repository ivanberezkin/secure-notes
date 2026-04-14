import Sidebar from "./components/Sidebar";

function App() {
  return (
    <div className="flex h-screen w-full bg-white overflow-hidden">
      <Sidebar />

      {/* This is your main workspace */}
      <main className="flex-1 h-full overflow-y-auto bg-gray-50 p-8">
        <header className="mb-6">
          <h1 className="text-3xl font-bold text-gray-900">My Notes</h1>
          <p className="text-gray-500">Select a note to start editing.</p>
        </header>

        {/* Placeholder for your notes grid or list */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          <div className="p-4 bg-white rounded-lg border border-gray-200 shadow-sm h-40">
            Note Content...
          </div>
        </div>
      </main>
    </div>
  );
}

export default App;
