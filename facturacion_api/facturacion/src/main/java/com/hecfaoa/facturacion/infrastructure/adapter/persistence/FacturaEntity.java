package com.hecfaoa.facturacion.infrastructure.adapter.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "facturas")
public class FacturaEntity {

    @Id
    private UUID id;
    private String clienteId;
    private LocalDateTime fechaEmision;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;

    // Constructor vacío requerido por JPA
    public FacturaEntity() {}

    public FacturaEntity(UUID id, String clienteId, LocalDateTime fechaEmision, BigDecimal subtotal, BigDecimal impuestos, BigDecimal total) {
        this.id = id;
        this.clienteId = clienteId;
        this.fechaEmision = fechaEmision;
        this.subtotal = subtotal;
        this.impuestos = impuestos;
        this.total = total;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getImpuestos() { return impuestos; }
    public void setImpuestos(BigDecimal impuestos) { this.impuestos = impuestos; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}
