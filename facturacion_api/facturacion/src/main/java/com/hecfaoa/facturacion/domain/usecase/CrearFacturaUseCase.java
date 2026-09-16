package com.hecfaoa.facturacion.domain.usecase;

import com.hecfaoa.facturacion.domain.model.Factura;
import com.hecfaoa.facturacion.domain.model.LineaFactura;
import com.hecfaoa.facturacion.domain.model.TipoImpuesto;
import com.hecfaoa.facturacion.domain.ports.FacturaWriterPort;
import com.hecfaoa.facturacion.domain.ports.ValidadorFacturaPort;
import com.hecfaoa.facturacion.domain.service.CalculadorImpuestoService;

import java.math.BigDecimal;
import java.util.List;

public class CrearFacturaUseCase {

    private final CalculadorImpuestoService calculadorImpuestoService;
    private final FacturaWriterPort facturaWriterPort;
    private final List<ValidadorFacturaPort> validadores;

    public CrearFacturaUseCase(
            CalculadorImpuestoService calculadorImpuestoService,
            FacturaWriterPort facturaWriterPort,
            List<ValidadorFacturaPort> validadores
    ) {
        this.calculadorImpuestoService = calculadorImpuestoService;
        this.facturaWriterPort = facturaWriterPort;
        this.validadores = validadores;
    }

    // Ahora recibe List<TipoImpuesto> tiposImpuestos
    public Factura ejecutar(String clienteId, List<LineaFactura> lineas, List<TipoImpuesto> tiposImpuestos) {
        Factura factura = new Factura(clienteId, lineas);

        // Llamada en PLURAL: calcularImpuestos(...)
        BigDecimal impuestos = calculadorImpuestoService.calcularImpuestos(tiposImpuestos, factura.getSubtotal());
        factura.aplicarImpuestos(impuestos);

        boolean esValida = validadores.stream().allMatch(v -> v.esValida(factura));
        if (!esValida) {
            throw new IllegalArgumentException("La factura no superó las reglas de validación del negocio.");
        }

        return facturaWriterPort.guardar(factura);
    }
}