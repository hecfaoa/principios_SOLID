export type TipoImpuesto = 'IVA_GENERAL' | 'IVA_REDUCIDO' | 'EXENTO' | 'IVA_GUERRA';

export interface LineaFactura {
  codigoProducto: string;
  descripcion: string;
  cantidad: number;
  precioUnitario: number;
}

export interface CrearFacturaRequest {
  clienteId: string;
  tiposImpuestos: TipoImpuesto[];
  lineas: LineaFactura[];
}

export interface FacturaResponse {
  id: string;
  clienteId: string;
  fechaEmision: string;
  subtotal: number;
  impuestos: number;
  total: number;
}