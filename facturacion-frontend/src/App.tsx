import React, { useState } from 'react';
import type { TipoImpuesto, LineaFactura, FacturaResponse } from './types/factura';
import { crearFacturaAPI } from './services/facturaService';

export default function App() {
  const [clienteId, setClienteId] = useState('CLI-VIP-001');
  const [impuestosSeleccionados, setImpuestosSeleccionados] = useState<TipoImpuesto[]>(['IVA_GENERAL']);
  const [lineas, setLineas] = useState<LineaFactura[]>([
    { codigoProducto: 'PROD-01', descripcion: 'Laptop Pro', cantidad: 1, precioUnitario: 1200 }
  ]);
  const [resultado, setResultado] = useState<FacturaResponse | null>(null);
  const [error, setError] = useState<string | null>(null);

  const toggleImpuesto = (tipo: TipoImpuesto) => {
    setImpuestosSeleccionados(prev =>
      prev.includes(tipo) ? prev.filter(i => i !== tipo) : [...prev, tipo]
    );
  };

  const handleAgregarLinea = () => {
    setLineas([...lineas, { codigoProducto: '', descripcion: '', cantidad: 1, precioUnitario: 0 }]);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    try {
      const data = await crearFacturaAPI({
        clienteId,
        tiposImpuestos: impuestosSeleccionados,
        lineas
      });
      setResultado(data);
    } catch (err: any) {
      setError(err.message || 'Error inesperado al conectar con Spring Boot');
    }
  };

  return (
    <div className="min-h-screen bg-slate-100 p-8 font-sans">
      <div className="max-w-4xl mx-auto bg-white rounded-xl shadow-md p-6 grid grid-cols-1 md:grid-cols-2 gap-6">
        
        {/* FORMULARIO DE EMISIÓN */}
        <div>
          <h2 className="text-2xl font-bold text-slate-800 mb-4">Nueva Factura</h2>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div>
              <label className="block text-sm font-medium text-slate-700">ID Cliente</label>
              <input
                type="text"
                value={clienteId}
                onChange={e => setClienteId(e.target.value)}
                className="w-full border rounded p-2 mt-1"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-slate-700 mb-2">Impuestos a Aplicar</label>
              <div className="flex flex-wrap gap-2">
                {(['IVA_GENERAL', 'IVA_REDUCIDO', 'EXENTO', 'IVA_GUERRA'] as TipoImpuesto[]).map(tipo => (
                  <button
                    key={tipo}
                    type="button"
                    onClick={() => toggleImpuesto(tipo)}
                    className={`px-3 py-1 rounded text-xs font-semibold transition-colors ${
                      impuestosSeleccionados.includes(tipo)
                        ? 'bg-indigo-600 text-white'
                        : 'bg-slate-200 text-slate-700 hover:bg-slate-300'
                    }`}
                  >
                    {tipo}
                  </button>
                ))}
              </div>
            </div>

            <div>
              <h3 className="text-sm font-medium text-slate-700 mb-2">Líneas de Factura</h3>
              {lineas.map((linea, index) => (
                <div key={index} className="grid grid-cols-3 gap-2 mb-2">
                  <input
                    placeholder="Código"
                    value={linea.codigoProducto}
                    onChange={e => {
                      const copy = [...lineas];
                      copy[index].codigoProducto = e.target.value;
                      setLineas(copy);
                    }}
                    className="border p-1 text-sm rounded"
                  />
                  <input
                    type="number"
                    placeholder="Cant"
                    value={linea.cantidad}
                    onChange={e => {
                      const copy = [...lineas];
                      copy[index].cantidad = Number(e.target.value);
                      setLineas(copy);
                    }}
                    className="border p-1 text-sm rounded"
                  />
                  <input
                    type="number"
                    placeholder="Precio"
                    value={linea.precioUnitario}
                    onChange={e => {
                      const copy = [...lineas];
                      copy[index].precioUnitario = Number(e.target.value);
                      setLineas(copy);
                    }}
                    className="border p-1 text-sm rounded"
                  />
                </div>
              ))}
              <button
                type="button"
                onClick={handleAgregarLinea}
                className="text-xs text-indigo-600 hover:underline mt-1 inline-block"
              >
                + Agregar producto
              </button>
            </div>

            <button
              type="submit"
              className="w-full bg-slate-900 text-white py-2 rounded font-semibold hover:bg-slate-800 transition"
            >
              Emitir Factura
            </button>
          </form>
        </div>

        {/* RESPUESTA DE LA API */}
        <div className="bg-slate-50 p-6 rounded-lg border border-slate-200 flex flex-col justify-between">
          <div>
            <h3 className="text-lg font-bold text-slate-800 mb-4">Resumen de Respuesta</h3>
            {error && <div className="p-3 bg-red-100 text-red-700 rounded text-sm mb-4">{error}</div>}
            {resultado ? (
              <div className="space-y-3 text-sm text-slate-700">
                <p><span className="font-semibold">ID Factura:</span> <br/><span className="text-xs font-mono bg-slate-200 p-1 rounded">{resultado.id}</span></p>
                <p><span className="font-semibold">Cliente:</span> {resultado.clienteId}</p>
                <hr className="my-2 border-slate-200"/>
                <p><span className="font-semibold">Subtotal:</span> ${resultado.subtotal.toFixed(2)}</p>
                <p><span className="font-semibold">Impuestos:</span> ${resultado.impuestos.toFixed(2)}</p>
                <p className="text-lg font-bold text-emerald-600"><span>Total:</span> ${resultado.total.toFixed(2)}</p>
              </div>
            ) : (
              <p className="text-sm text-slate-400 italic">Completa el formulario para enviar la petición a la API Spring Boot.</p>
            )}
          </div>
        </div>

      </div>
    </div>
  );
}