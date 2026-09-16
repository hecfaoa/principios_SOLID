package com.hecfaoa.facturacion.domain.ports;

import com.hecfaoa.facturacion.domain.model.Factura;

public interface ValidadorFacturaPort {
    /**
     * Contrato LSP: Debe retornar 'true' si es válida o 'false' si no lo es.
     * NINGUNA implementación debe lanzar un RuntimeException inesperado.
     */
    boolean esValida(Factura factura);
}