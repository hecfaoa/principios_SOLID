package com.hecfaoa.facturacion.infrastructure.adapter.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record FacturaResponse(
    UUID id,
    String clienteId,
    LocalDateTime fechaEmision,
    BigDecimal subtotal,
    BigDecimal impuestos,
    BigDecimal total
) {}