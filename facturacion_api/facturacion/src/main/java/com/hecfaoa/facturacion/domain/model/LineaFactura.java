package com.hecfaoa.facturacion.domain.model;

import java.math.BigDecimal;
/**
 * Representa un ítem o producto individual dentro de la factura.
 * Usamos 'record' (Java 17+) porque es una estructura de datos inmutable.
 */
public record LineaFactura(
    String codigoProducto,
    String descripcion,
    int cantidad,
    BigDecimal precioUnitario
) {
    // Constructor con validaciones básicas de integridad
    public LineaFactura {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        if (precioUnitario == null || precioUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio unitario debe ser mayor a cero.");
        }
    }

    // Calcula el subtotal de este producto multiplicando cantidad por precio
    public BigDecimal getSubtotal() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}
