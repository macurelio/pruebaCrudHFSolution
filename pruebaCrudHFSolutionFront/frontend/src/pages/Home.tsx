// src/pages/Home.tsx
import React from "react";
import { Link } from "react-router-dom";

const Home: React.FC = () => (
  <div className="text-center mt-5">
    <h1 className="fw-bold text-primary">Bienvenido al Desafío Técnico 2025</h1>
    <p className="lead">CRUD de Personas — Frontend con React + Vite + TypeScript</p>
    <div className="d-flex justify-content-center gap-3 mt-4">
      <Link to="/persons" className="btn btn-lg btn-outline-primary">📋 Ver Personas</Link>
      <Link to="/persons/create" className="btn btn-lg btn-success">➕ Agregar Persona</Link>
    </div>
  </div>
);

export default Home;
