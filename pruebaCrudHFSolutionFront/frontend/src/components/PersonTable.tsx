// src/components/PersonTable.tsx
import React from "react";
import type { Person } from "../services/personService";
import { Link } from "react-router-dom";

interface Props {
  persons: Person[];
  onDelete: (rut: string) => void;
}

const calcularEdad = (fecha: string) => {
  if (!fecha) return "-";
  const n = new Date(fecha);
  const diff = Date.now() - n.getTime();
  return Math.floor(diff / (1000 * 60 * 60 * 24 * 365.25));
};

const PersonTable: React.FC<Props> = ({ persons, onDelete }) => (
  <div className="table-responsive">
    <table className="table table-bordered table-striped shadow-sm">
      <thead className="table-dark">
        <tr>
          <th>RUT</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Edad</th>
          <th>Dirección</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        {persons.length === 0 ? (
          <tr>
            <td colSpan={6} className="text-center">No hay personas</td>
          </tr>
        ) : (
          persons.map((p) => (
            <tr key={p.rut}>
              <td>{p.rut}</td>
              <td>{p.nombre}</td>
              <td>{p.apellido}</td>
              <td>{calcularEdad(p.fechaNacimiento)}</td>
              <td>{p.direccion.calle}, {p.direccion.comuna}, {p.direccion.region}</td>
              <td>
                <Link to={`/persons/edit/${p.rut}`} className="btn btn-warning btn-sm me-2">
                  ✏️ Editar
                </Link>
                <button className="btn btn-danger btn-sm" onClick={() => onDelete(p.rut)}>
                  🗑 Eliminar
                </button>
              </td>
            </tr>
          ))
        )}
      </tbody>
    </table>
  </div>
);

export default PersonTable;
