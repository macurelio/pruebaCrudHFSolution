// src/components/LoadingSpinner.tsx
import React from "react";

const LoadingSpinner: React.FC = () => (
  <div className="text-center my-4">
    <div className="spinner-border" role="status">
      <span className="visually-hidden">Cargando...</span>
    </div>
  </div>
);

export default LoadingSpinner;
