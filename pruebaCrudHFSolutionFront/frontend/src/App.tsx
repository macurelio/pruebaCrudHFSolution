import { Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import PersonList from "./pages/PersonList";
import PersonEdit from "./pages/PersonEdit";
import PersonCreate from "./pages/PersonCreate";

function App() {
  return (
    <div>
      <Navbar />
      <div className="container mt-4">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/persons" element={<PersonList />} />
          <Route path="/persons/create" element={<PersonCreate />} />
          <Route path="/persons/edit/:id" element={<PersonEdit />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
