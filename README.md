# Desafío Técnico 2025 - Solución

## 📌 Objetivo
Este proyecto fue desarrollado como solución al **Desafío Técnico 2025**, cuyo objetivo principal es demostrar habilidades técnicas en el desarrollo de un sistema **CRUD de personas** con validaciones, persistencia confiable, manejo de errores y exposición de endpoints REST.

---

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 21**
- **Spring Boot 3.5.4+**
- **Maven**
- **Hibernate / JPA**
- **MySQL** como base de datos relacional
- **Lombok** para reducir boilerplate
- **JUnit + Mockito** para pruebas unitarias

### Frontend
- **React 18**
- **Vite** como empaquetador
- **TypeScript**
- **Bootstrap 5** para el diseño responsivo
- **Axios** para el consumo de APIs

---

## ⚙️ Requerimientos Funcionales Implementados
- CRUD de **Personas** con los siguientes campos:
  - Rut (validación formato chileno con puntos y guion)
  - Nombre
  - Apellido
  - Fecha de nacimiento (con cálculo automático de la edad)
  - Dirección: Calle – Comuna – Región
- Validaciones de entrada (backend + frontend).
- Persistencia asegurada incluso si la BD no está disponible temporalmente.
- Manejo centralizado de errores y respuestas HTTP claras.
- Visualización de la edad actual en el frontend.

---

## 🗄️ Base de Datos
El proyecto incluye:
- **Schema SQL** (`schema.sql`) con la estructura de la base de datos.
- **Data de ejemplo** (`data.sql`) precargada para pruebas iniciales.

Ejemplo de tabla `persona`:

```sql
CREATE TABLE persona (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(12) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    calle VARCHAR(150),
    comuna VARCHAR(100),
    region VARCHAR(100)
);
```

---

## ✅ Pruebas Unitarias
- Se implementaron **TestUnitarios** en el backend con JUnit5 y Mockito.
- Cobertura en:
  - Servicios (reglas de negocio).
  - Repositorios (persistencia).
  - Controladores (endpoints REST).

---

## 🚀 Ejecución del Proyecto

### Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

El backend se levantará en:  
👉 `http://localhost:8080/api/v1/personas`

### Frontend
```bash
cd frontend
npm install
npm run dev
```

El frontend se levantará en:  
👉 `http://localhost:5173`

---

## 🔗 Postman Collection
Se incluye una **colección de pruebas en Postman** con todos los endpoints listos para ser consumidos:  
[👉 Colección Postman - Desafío Técnico 2025](https://macureli0.postman.co/workspace/TEST-HFSOLUTION~cbfb560d-5a78-4a5c-a0af-049ae1d8112f/collection/19036015-8ca59a80-b82c-4d59-9159-4d131c40ce08?action=share&source=copy-link&creator=19036015)

---

## 📂 Estructura del Proyecto
```
DesafioTecnico2025/
│── backend/
│   ├── src/main/java/com/desafio/api/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   └── model/
│   ├── src/test/java/com/desafio/api/
│   └── pom.xml
│
│── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   └── App.tsx
│   └── package.json
│
│── docs/
│   ├── schema.sql
│   ├── data.sql
│   └── README.md
```

---

## 📝 Observaciones
- Se aplicaron buenas prácticas de **código limpio** y modular.
- El frontend fue estilizado con **Bootstrap** para mejorar la experiencia visual.
- Los supuestos del desafío se documentaron en este archivo.
