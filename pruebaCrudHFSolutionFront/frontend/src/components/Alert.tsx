// src/components/Alert.tsx
import React from "react";

interface Props {
  variant?: "success" | "danger" | "warning" | "info";
  children: React.ReactNode;
  onClose?: () => void;
}

const Alert: React.FC<Props> = ({ variant = "info", children, onClose }) => {
  return (
    <div className={`alert alert-${variant} alert-dismissible`} role="alert">
      {children}
      {onClose && (
        <button type="button" className="btn-close" aria-label="Close" onClick={onClose}></button>
      )}
    </div>
  );
};

export default Alert;
