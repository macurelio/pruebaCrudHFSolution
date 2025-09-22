Person CRUD - Frontend

Frontend del desafío técnico 2025.
Construido con React + Vite + TypeScript y estilado con Bootstrap 5.

Este proyecto consume los endpoints REST expuestos por el backend Spring Boot (/api/v1/persons).

🚀 Características

CRUD de Personas: Crear, listar, ver detalle y eliminar registros.

Integración con Backend: Proxy de Vite configurado para apuntar a http://localhost:8080.

Estilos con Bootstrap 5: Diseño responsive y rápido de implementar.

Hooks reutilizables: useAsync para manejo de llamadas a la API.

Logger de clicks: Todos los clicks relevantes en botones y enlaces se registran en la consola (src/utils/logger.ts).

📦 Requisitos

Node.js 18+

npm o yarn

Backend en ejecución (Spring Boot) en http://localhost:8080

⚙️ Instalación

Clona el repositorio y entra en la carpeta del frontend:

git clone https://github.com/<tu-usuario>/person-crud-frontend.git
cd person-crud-frontend


Instala dependencias:

npm install

▶️ Ejecutar en desarrollo
npm run dev


Accede en el navegador a:

http://localhost:3000


El proxy en vite.config.ts redirige automáticamente todas las llamadas a /api hacia http://localhost:8080.

🛠️ Scripts útiles

npm run dev → Ejecuta en modo desarrollo (con HMR).

npm run build → Compila la app para producción.

npm run preview → Levanta un servidor local para probar la build.

📁 Estructura principal
src/
├── App.tsx               # Configuración de rutas
├── main.tsx              # Entry point
├── styles.css            # Estilos globales
├── types.ts              # Interfaces (PersonDTO)
├── utils/logger.ts       # Registro de clicks
├── services/api.ts       # Cliente Axios
├── hooks/useAsync.ts     # Hook genérico para llamadas asíncronas
└── components/
    ├── PersonList.tsx    # Listado de personas
    ├── PersonForm.tsx    # Formulario de creación
    └── PersonDetail.tsx  # Vista de detalle

🔗 Endpoints consumidos

GET /api/v1/persons → Listar personas

GET /api/v1/persons/{rut} → Obtener persona por RUT

POST /api/v1/persons → Crear persona

PUT /api/v1/persons/{rut} → Actualizar persona

DELETE /api/v1/persons/{rut} → Eliminar persona

📸 Ejemplo de uso

Crear persona desde el formulario.

Revisar en la lista (tabla con Bootstrap).

Clic en "Ver" → Detalle de la persona.

Clic en "Eliminar" → Registro eliminado y refresco automático.

En consola verás logs de los clics:

[CLICK] { action: "Crear persona 12.345.678-9", timestamp: "2025-09-20T17:15:00.000Z" }
