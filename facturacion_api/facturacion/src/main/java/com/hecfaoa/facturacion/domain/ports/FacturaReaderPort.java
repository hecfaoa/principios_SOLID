package com.hecfaoa.facturacion.domain.ports;

import com.hecfaoa.facturacion.domain.model.Factura;
import java.util.Optional;
import java.util.UUID;

/**
 * ISP: Interfaz delgada enfocada EXCLUSIVAMENTE en lecturas (auditoría, reportes).
 */
public interface FacturaReaderPort {
    Optional<Factura> buscarPorId(UUID id);
}
