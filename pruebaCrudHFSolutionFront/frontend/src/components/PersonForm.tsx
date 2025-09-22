import { useState, type FormEvent } from "react";
import type { ChangeEvent } from "react";
import type { Person } from "../services/personService";

interface Props {
  initialData?: Person;
  onSubmit: (person: Person) => void;
}

export default function PersonForm({ initialData, onSubmit }: Props) {
  const [form, setForm] = useState<Person>(
    initialData || {
      rut: "",
      nombre: "",
      apellido: "",
      fechaNacimiento: "",
      direccion: { calle: "", comuna: "", region: "" },
    }
  );

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    if (["calle", "comuna", "region"].includes(name)) {
      setForm({ ...form, direccion: { ...form.direccion, [name]: value } });
    } else {
      setForm({ ...form, [name]: value });
    }
  };

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    onSubmit(form);
  };

  return (
    <form onSubmit={handleSubmit} className="card p-4 shadow">
      <div className="mb-3">
        <label className="form-label">RUT</label>
        <input
          type="text"
          className="form-control"
          name="rut"
          value={form.rut}
          onChange={handleChange}
          required
        />
      </div>
      <div className="row">
        <div className="col mb-3">
          <label className="form-label">Nombre</label>
          <input
            type="text"
            className="form-control"
            name="nombre"
            value={form.nombre}
            onChange={handleChange}
            required
          />
        </div>
        <div className="col mb-3">
          <label className="form-label">Apellido</label>
          <input
            type="text"
            className="form-control"
            name="apellido"
            value={form.apellido}
            onChange={handleChange}
            required
          />
        </div>
      </div>
      <div className="mb-3">
        <label className="form-label">Fecha de Nacimiento</label>
        <input
          type="date"
          className="form-control"
          name="fechaNacimiento"
          value={form.fechaNacimiento}
          onChange={handleChange}
          required
        />
      </div>
      <h5>Dirección</h5>
      <div className="mb-3">
        <label className="form-label">Calle</label>
        <input
          type="text"
          className="form-control"
          name="calle"
          value={form.direccion.calle}
          onChange={handleChange}
          required
        />
      </div>
      <div className="mb-3">
        <label className="form-label">Comuna</label>
        <input
          type="text"
          className="form-control"
          name="comuna"
          value={form.direccion.comuna}
          onChange={handleChange}
          required
        />
      </div>
      <div className="mb-3">
        <label className="form-label">Región</label>
        <input
          type="text"
          className="form-control"
          name="region"
          value={form.direccion.region}
          onChange={handleChange}
          required
        />
      </div>
      <button type="submit" className="btn btn-primary">
        Guardar
      </button>
    </form>
  );
}
