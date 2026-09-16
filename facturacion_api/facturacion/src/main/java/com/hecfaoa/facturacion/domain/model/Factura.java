package com.hecfaoa.facturacion.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Factura {
    private final UUID id;
    private final String clienteId;
    private final List<LineaFactura> lineas;
    private final LocalDateTime fechaEmision;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;

    public Factura(String clienteId, List<LineaFactura> lineas) {
        if (lineas == null || lineas.isEmpty()) {
            throw new IllegalArgumentException("Una factura debe tener al menos una línea.");
        }
        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.lineas = List.copyOf(lineas);
        this.fechaEmision = LocalDateTime.now();
        this.subtotal = calcularSubtotal();
        this.impuestos = BigDecimal.ZERO;
        this.total = this.subtotal;
    }

    private BigDecimal calcularSubtotal() {
        return lineas.stream()
                .map(LineaFactura::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void aplicarImpuestos(BigDecimal montoImpuestos) {
        this.impuestos = montoImpuestos;
        this.total = this.subtotal.add(montoImpuestos);
    }

    // Getters
    public UUID getId() { return id; }
    public String getClienteId() { return clienteId; }
    public List<LineaFactura> getLineas() { return lineas; }
    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public BigDecimal getSubtotal() { return subtotal; }
    public BigDecimal getImpuestos() { return impuestos; }
    public BigDecimal getTotal() { return total; }
}