package com.hecfaoa.facturacion.infrastructure.adapter.impuesto;

import com.hecfaoa.facturacion.domain.model.TipoImpuesto;
import com.hecfaoa.facturacion.domain.ports.EstrategiaImpuesto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ImpuestoExentoAdapter implements EstrategiaImpuesto {

    @Override
    public boolean aplicaPara(TipoImpuesto tipoImpuesto) {
        return TipoImpuesto.EXENTO.equals(tipoImpuesto);
    }

    @Override
    public BigDecimal calcular(BigDecimal subtotal) {
        return BigDecimal.ZERO;
    }
}