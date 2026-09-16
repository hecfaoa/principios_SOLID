package com.hecfaoa.facturacion.infrastructure.adapter.impuesto;

import com.hecfaoa.facturacion.domain.model.TipoImpuesto;
import com.hecfaoa.facturacion.domain.ports.EstrategiaImpuesto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class IvaGuerraAdapter implements EstrategiaImpuesto {

    private static final BigDecimal TASA_10 = new BigDecimal("0.10");

    @Override
    public boolean aplicaPara(TipoImpuesto tipoImpuesto) {
        return TipoImpuesto.IVA_GUERRA.equals(tipoImpuesto);
    }

    @Override
    public BigDecimal calcular(BigDecimal subtotal) {
        return subtotal == null ? BigDecimal.ZERO : subtotal.multiply(TASA_10);
    }
}