// src/pages/PersonCreate.tsx
import React, { useState } from "react";
import { createPerson, type Person } from "../services/personService";
import PersonForm from "../components/PersonForm";
import Alert from "../components/Alert";
import { useNavigate } from "react-router-dom";

const PersonCreate: React.FC = () => {
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);
  const navigate = useNavigate();

  const handleSubmit = async (person: Person) => {
    try {
      await createPerson(person);
      setSuccess("Persona creada correctamente");
      setTimeout(() => navigate("/persons"), 1200);
    } catch {
      setError("Error creando persona");
    }
  };

  return (
    <div>
      <h2>Crear Persona</h2>
      {success && <Alert variant="success">{success}</Alert>}
      {error && <Alert variant="danger" onClose={() => setError(null)}>{error}</Alert>}
      <PersonForm onSubmit={handleSubmit} />
    </div>
  );
};

export default PersonCreate;
