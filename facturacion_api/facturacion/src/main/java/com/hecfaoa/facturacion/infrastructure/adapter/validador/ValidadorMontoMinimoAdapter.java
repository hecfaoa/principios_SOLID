package com.hecfaoa.facturacion.infrastructure.adapter.validador;

import com.hecfaoa.facturacion.domain.model.Factura;
import com.hecfaoa.facturacion.domain.ports.ValidadorFacturaPort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidadorMontoMinimoAdapter implements ValidadorFacturaPort {

    @Override
    public boolean esValida(Factura factura) {
        if (factura == null || factura.getTotal() == null) {
            return false;
        }
        // La factura es válida si el total es superior a cero
        return factura.getTotal().compareTo(BigDecimal.ZERO) > 0;
    }
}