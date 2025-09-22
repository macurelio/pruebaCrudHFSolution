// src/pages/PersonList.tsx
import React, { useEffect, useState } from "react";
import { getAllPersons, deletePerson, type Person } from "../services/personService";
import PersonTable from "../components/PersonTable";
import LoadingSpinner from "../components/LoadingSpinner";
import Alert from "../components/Alert";

const PersonList: React.FC = () => {
  const [persons, setPersons] = useState<Person[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [message, setMessage] = useState<string | null>(null);

  const fetch = async () => {
    try {
      setLoading(true);
      const res = await getAllPersons();
      setPersons(res.data);
    } catch {
      setError("Error al obtener personas (servidor).");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { fetch(); }, []);

  const handleDelete = async (rut?: string) => {
    if (!rut) return;
    try { await deletePerson(rut); setMessage("Persona eliminada"); fetch(); }
    catch { setError("Error al eliminar"); }
  };

  return (
    <div>
      <h2>Listado de Personas</h2>
      {message && <Alert variant="success" onClose={() => setMessage(null)}>{message}</Alert>}
      {error && <Alert variant="danger" onClose={() => setError(null)}>{error}</Alert>}
      {loading ? <LoadingSpinner /> : <PersonTable persons={persons} onDelete={handleDelete} />}
    </div>
  );
};

export default PersonList;
