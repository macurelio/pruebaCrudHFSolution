// src/__tests__/PersonCreate.test.tsx
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import PersonCreate from "../pages/PersonCreate";
import * as service from "../services/personService";
import { vi } from "vitest";

vi.mock("../services/personService");
vi.mock("react-router-dom", async () => {
  const actual = await vi.importActual<any>("react-router-dom");
  return {
    ...actual,
    useNavigate: () => vi.fn()
  };
});

const mockedCreate = vi.spyOn(service, "createPerson");

describe("PersonCreate", () => {
  afterEach(() => vi.clearAllMocks());

  it("crea persona y muestra mensaje de éxito", async () => {
    mockedCreate.mockResolvedValue({ data: { rut: "12345678-9" } });

    render(<PersonCreate />);

    fireEvent.change(screen.getByLabelText(/RUT/i), { target: { value: "12345678-9" } });
    fireEvent.change(screen.getByLabelText(/Nombre/i), { target: { value: "Test" } });
    fireEvent.change(screen.getByLabelText(/Apellido/i), { target: { value: "User" } });
    fireEvent.change(screen.getByLabelText(/Fecha de Nacimiento/i), { target: { value: "1995-05-05" } });
    fireEvent.change(screen.getByLabelText(/Calle/i), { target: { value: "Calle 1" } });
    fireEvent.change(screen.getByLabelText(/Comuna/i), { target: { value: "Comuna" } });
    fireEvent.change(screen.getByLabelText(/Región/i), { target: { value: "Region" } });

    fireEvent.click(screen.getByRole("button", { name: /guardar/i }));

    await waitFor(() => {
      expect(screen.getByText(/Persona creada correctamente/i)).toBeInTheDocument();
    });

    expect(mockedCreate).toHaveBeenCalled();
  });

  it("muestra error si createPerson falla", async () => {
    mockedCreate.mockRejectedValue(new Error("fail"));

    render(<PersonCreate />);

    fireEvent.change(screen.getByLabelText(/RUT/i), { target: { value: "12345678-9" } });
    fireEvent.change(screen.getByLabelText(/Nombre/i), { target: { value: "Test" } });
    fireEvent.change(screen.getByLabelText(/Apellido/i), { target: { value: "User" } });
    fireEvent.change(screen.getByLabelText(/Fecha de Nacimiento/i), { target: { value: "1995-05-05" } });
    fireEvent.change(screen.getByLabelText(/Calle/i), { target: { value: "Calle 1" } });
    fireEvent.change(screen.getByLabelText(/Comuna/i), { target: { value: "Comuna" } });
    fireEvent.change(screen.getByLabelText(/Región/i), { target: { value: "Region" } });

    fireEvent.click(screen.getByRole("button", { name: /guardar/i }));

    await waitFor(() =>
      expect(screen.getByText(/Error creando persona/i)).toBeInTheDocument()
    );
  });
});
