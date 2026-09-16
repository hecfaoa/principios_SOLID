import type { CrearFacturaRequest, FacturaResponse } from '../types/factura';

const API_BASE_URL = 'http://localhost:8080/api/v1/facturas';

export const crearFacturaAPI = async (payload: CrearFacturaRequest): Promise<FacturaResponse> => {
  const response = await fetch(API_BASE_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload),
  });

  if (!response.ok) {
    throw new Error('Error al procesar la factura en el servidor');
  }

  return response.json();
};