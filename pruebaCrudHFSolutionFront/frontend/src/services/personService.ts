// src/services/personService.ts
import axios from "axios";

export interface Person {
  rut: string;
  nombre: string;
  apellido: string;
  fechaNacimiento: string;
  direccion: {
    calle: string;
    comuna: string;
    region: string;
  };
}

const API_BASE = (import.meta.env.VITE_API_URL as string) ?? "http://localhost:8080/api/v1";
const api = axios.create({ baseURL: API_BASE, timeout: 5000 });

export const getAllPersons = () => api.get<Person[]>("/persons");

export const getPersonByRut = (rut: string) => api.get<Person>(`/persons/${rut}`);

export const createPerson = (person: Person) => api.post<Person>("/persons", person);

export const updatePerson = (rut: string, person: Person) =>
  api.put<Person>(`/persons/${rut}`, person);

export const deletePerson = (rut: string) => api.delete<void>(`/persons/${rut}`);
