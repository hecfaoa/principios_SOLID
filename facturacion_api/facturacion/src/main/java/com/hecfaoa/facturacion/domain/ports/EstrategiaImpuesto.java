package com.hecfaoa.facturacion.domain.ports;

import com.hecfaoa.facturacion.domain.model.TipoImpuesto;
import java.math.BigDecimal;

public interface EstrategiaImpuesto {
    
    // Identifica para qué tipo de impuesto aplica esta clase
    boolean aplicaPara(TipoImpuesto tipoImpuesto);
    
    // Ejecuta el cálculo matemático del impuesto sobre el subtotal
    BigDecimal calcular(BigDecimal subtotal);
}