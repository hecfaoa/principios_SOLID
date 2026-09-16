package com.hecfaoa.facturacion.infrastructure.adapter.impuesto;

import com.hecfaoa.facturacion.domain.model.TipoImpuesto;
import com.hecfaoa.facturacion.domain.ports.EstrategiaImpuesto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class IvaGeneralAdapter implements EstrategiaImpuesto {

    private static final BigDecimal TASA_16 = new BigDecimal("0.16");

    @Override
    public boolean aplicaPara(TipoImpuesto tipoImpuesto) {
        return TipoImpuesto.IVA_GENERAL.equals(tipoImpuesto);
    }

    @Override
    public BigDecimal calcular(BigDecimal subtotal) {
        return subtotal == null ? BigDecimal.ZERO : subtotal.multiply(TASA_16);
    }
}