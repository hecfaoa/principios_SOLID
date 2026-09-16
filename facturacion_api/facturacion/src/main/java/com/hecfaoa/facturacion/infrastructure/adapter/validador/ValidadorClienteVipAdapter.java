package com.hecfaoa.facturacion.infrastructure.adapter.validador;

import com.hecfaoa.facturacion.domain.model.Factura;
import com.hecfaoa.facturacion.domain.ports.ValidadorFacturaPort;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteVipAdapter implements ValidadorFacturaPort {

    @Override
    public boolean esValida(Factura factura) {
        if (factura == null || factura.getClienteId() == null) {
            return false;
        }
        // Respeta el contrato LSP retornando un booleano sin alterar los invariantes
        return factura.getClienteId().startsWith("VIP");
    }
}