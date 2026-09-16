# 🧾 Sistema de Facturación "Nivel 1%"

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![React](https://img.shields.io/badge/React-18+-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![TypeScript](https://img.shields.io/badge/TypeScript-5.x-3178C6?style=for-the-badge&logo=typescript&logoColor=white)
![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-v3-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white)
![pnpm](https://img.shields.io/badge/pnpm-v11-F69220?style=for-the-badge&logo=pnpm&logoColor=white)

Un sistema de facturación electrónica de clase mundial diseñado bajo **Arquitectura Hexagonal (Puertos y Adaptadores)** y guiado por una implementación estricta de los **Principios SOLID**. Integra un backend reactivo en **Java con Spring Boot** y un cliente web desacoplado en **React, TypeScript y Tailwind CSS**.

---

## 🏛️ Principios SOLID y Diseño de Arquitectura

El propósito central de este proyecto es demostrar cómo la teoría de diseño de software se traduce en código robusto, desacoplado y listo para producción.

| Principio | Aplicación en el Proyecto |
| :--- | :--- |
| **SRP** *(Single Responsibility)* | Separación estricta: los Casos de Uso orquestan procesos, los Controladores manejan la capa HTTP y las Entidades ejecutan las reglas financieras. |
| **OCP** *(Open/Closed)* | Motor de cálculo de impuestos basado en el patrón **Strategy**. Agregar nuevos impuestos (`IVA_REDUCIDO`, `IVA_GUERRA`) no modifica el motor existente. |
| **LSP** *(Liskov Substitution)* | Validadores de negocio (`ValidadorFacturaPort`) intercambiables y polimórficos sin romper el flujo del Caso de Uso. |
| **ISP** *(Interface Segregation)* | Puertos de persistencia segregados en interfaces específicas de lectura y escritura para evitar acoplamiento innecesario. |
| **DIP** *(Dependency Inversion)* | El Core de Dominio no depende de ningún *framework*. Las dependencias apuntan hacia adentro mediante Puertos y Adaptadores. |

---

## 💻 Stack Tecnológico

<div align="center">

| Capa | Tecnología | Descripción |
| :--- | :--- | :--- |
| **Backend** | Java 17+ & Spring Boot 3.x | API REST y lógica de dominio con Arquitectura Limpia |
| **Persistencia** | JPA / H2 / PostgreSQL | Estrategia multi-perfil (Dev en memoria / Prod en BD relacional) |
| **Frontend** | React 18 + Vite | Cliente reactivo con empaquetado ultrarrápido |
| **Tipado** | TypeScript | Modo estricto (`verbatimModuleSyntax`) para contratos de datos |
| **Estilos** | Tailwind CSS v3 | Diseño adaptativo y moderno basado en utilidades |
| **Gestor** | pnpm | Manejo eficiente y veloz de dependencias |

</div>

---

## 🏗️ Estructura del Monorepo

```plaintext
facturacion-system/
├── ⚙️ backend/                        # Spring Boot (Arquitectura Hexagonal)
│   └── src/main/java/com/hecfaoa/facturacion/
│       ├── domain/                    # Núcleo puro (Sin dependencias externas)
│       │   ├── model/                 # Entidades financieras (Factura, LineaFactura)
│       │   ├── usecase/               # Casos de uso (CrearFacturaUseCase)
│       │   ├── service/               # Servicios de dominio (CalculadorImpuestoService)
│       │   └── ports/                 # Puertos/Interfaces (Lectura, Escritura, Validación)
│       └── infrastructure/            # Adaptadores externos
│           ├── adapter/rest/          # Controllers, DTOs y configuración CORS
│           ├── adapter/persistence/   # Repositorios JPA y entidades de BD
│           └── config/                # Inyección de dependencias de Spring
│
└── 🎨 frontend/                       # React + TypeScript + Vite
    └── src/
        ├── types/                     # Contratos de datos TypeScript (factura.ts)
        ├── services/                  # Capa Proxy HTTP desacoplada (facturaService.ts)
        ├── App.tsx                    # UI interactiva para emisión de facturas
        └── index.css                  # Directivas globales de Tailwind CSS

```
---

## 🚀 Instalación y Ejecución Local

### 📋 Requisitos Previos

Asegúrate de contar con los siguientes entornos configurados en tu sistema:

* **Java SDK:** `17+`
* **Node.js:** `18+`
* **Gestor de Paquetes:** `pnpm` (Instalación global: `npm install -g pnpm`)

---

### 1️⃣ Levantar el Backend (Spring Boot)

Abre una terminal en la raíz del proyecto y ejecuta:

```bash
cd backend
./mvnw spring-boot:run
```
---

> 📍 **API Endpoint activo:** `http://localhost:8080/api/v1/facturas`

---

### 2️⃣ Levantar el Frontend (React)

En una segunda terminal, instala las dependencias e inicia el servidor de desarrollo:

```bash
cd frontend
pnpm install
pnpm dev
```
---

###📍 Aplicación Web activa: http://localhost:5173

###🧪 Petición de Prueba
Puedes probar el envío de datos a la API mediante Postman, cURL o la herramienta HTTP de tu preferencia.

Método: POST

URL: http://localhost:8080/api/v1/facturas

Headers: Content-Type: application/json

Ejemplo de Payload (JSON):
```
JSON
{
  "clienteId": "CLI-VIP-001",
  "tiposImpuestos": [
    "IVA_GENERAL",
    "IVA_GUERRA"
  ],
  "lineas": [
    {
      "codigoProducto": "PROD-01",
      "descripcion": "Laptop Pro",
      "cantidad": 1,
      "precioUnitario": 1200.00
    }
  ]
}
```
