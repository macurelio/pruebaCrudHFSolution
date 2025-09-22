// src/__tests__/PersonList.test.tsx
import React from "react";
import { render, screen, waitFor } from "@testing-library/react";
import PersonList from "../pages/PersonList";
import * as service from "../services/personService";
import { vi } from "vitest";

vi.mock("../services/personService");

const mockedGetAll = vi.spyOn(service, "getAllPersons");

describe("PersonList", () => {
  afterEach(() => {
    vi.clearAllMocks();
  });

  it("muestra la tabla con personas cuando la API responde", async () => {
    const fake = [
      {
        rut: "11111111-1",
        nombre: "Juan",
        apellido: "Perez",
        fechaNacimiento: "1990-01-01",
        direccion: { calle: "C1", comuna: "S", region: "R" }
      }
    ];
    mockedGetAll.mockResolvedValue({ data: fake });

    render(<PersonList />);

    expect(screen.getByText(/Listado de Personas/i)).toBeInTheDocument();
    await waitFor(() => {
      expect(screen.getByText("Juan")).toBeInTheDocument();
      expect(screen.getByText("11111111-1")).toBeInTheDocument();
    });
  });

  it("muestra error si la API falla", async () => {
    mockedGetAll.mockRejectedValue(new Error("fail"));
    render(<PersonList />);

    await waitFor(() => {
      expect(screen.getByText(/Error al obtener personas/i)).toBeInTheDocument();
    });
  });
});
