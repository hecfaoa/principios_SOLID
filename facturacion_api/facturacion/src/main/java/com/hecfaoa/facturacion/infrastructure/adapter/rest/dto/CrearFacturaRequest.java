package com.hecfaoa.facturacion.infrastructure.adapter.rest.dto;

import com.hecfaoa.facturacion.domain.model.TipoImpuesto;
import java.math.BigDecimal;
import java.util.List; // <-- ESTE IMPORT ES EL QUE HACE FALTA

public record CrearFacturaRequest(
    String clienteId,
    List<TipoImpuesto> tiposImpuestos,
    List<LineaRequest> lineas
) {
    public record LineaRequest(
        String codigoProducto,
        String descripcion,
        int cantidad,
        BigDecimal precioUnitario
    ) {}
}