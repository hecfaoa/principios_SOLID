package com.hecfaoa.facturacion.domain.ports;

import com.hecfaoa.facturacion.domain.model.Factura;

/**
 * ISP: Interfaz delgada enfocada EXCLUSIVAMENTE en persistencia/modificación.
 */
public interface FacturaWriterPort {
    Factura guardar(Factura factura);
}