// src/__tests__/PersonForm.test.tsx
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import PersonForm from "../components/PersonForm";
import { vi } from "vitest";

describe("PersonForm", () => {
  it("muestra error si el RUT es inválido", async () => {
    const mockSubmit = vi.fn();
    render(<PersonForm onSubmit={mockSubmit} />);

    fireEvent.change(screen.getByLabelText(/RUT/i), { target: { value: "123" } });
    fireEvent.change(screen.getByLabelText(/Nombre/i), { target: { value: "Juan" } });
    fireEvent.change(screen.getByLabelText(/Apellido/i), { target: { value: "Perez" } });

    fireEvent.click(screen.getByRole("button", { name: /guardar/i }));

    expect(await screen.findByText(/RUT formato inválido/i)).toBeInTheDocument();
    expect(mockSubmit).not.toHaveBeenCalled();
  });

  it("llama onSubmit si los datos son válidos", async () => {
    const mockSubmit = vi.fn(() => Promise.resolve());
    render(<PersonForm onSubmit={mockSubmit} />);

    fireEvent.change(screen.getByLabelText(/RUT/i), { target: { value: "12345678-9" } });
    fireEvent.change(screen.getByLabelText(/Nombre/i), { target: { value: "Ana" } });
    fireEvent.change(screen.getByLabelText(/Apellido/i), { target: { value: "Gomez" } });
    fireEvent.change(screen.getByLabelText(/Fecha de Nacimiento/i), { target: { value: "1990-01-01" } });
    fireEvent.change(screen.getByLabelText(/Calle/i), { target: { value: "Av. 1" } });
    fireEvent.change(screen.getByLabelText(/Comuna/i), { target: { value: "Santiago" } });
    fireEvent.change(screen.getByLabelText(/Región/i), { target: { value: "RM" } });

    fireEvent.click(screen.getByRole("button", { name: /guardar/i }));

    await waitFor(() => expect(mockSubmit).toHaveBeenCalled());
  });
});
