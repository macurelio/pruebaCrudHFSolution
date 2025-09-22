// src/components/Navbar.tsx
import React from "react";
import { Link, NavLink } from "react-router-dom";

const Navbar: React.FC = () => (
  <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
    <div className="container">
      <Link className="navbar-brand fw-bold" to="/">Desafío 2025</Link>
      <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="nav">
        <ul className="navbar-nav ms-auto">
          <li className="nav-item"><NavLink className="nav-link" to="/">Inicio</NavLink></li>
          <li className="nav-item"><NavLink className="nav-link" to="/persons">Personas</NavLink></li>
          <li className="nav-item"><NavLink className="nav-link" to="/persons/create">Crear</NavLink></li>
        </ul>
      </div>
    </div>
  </nav>
);

export default Navbar;
