package com.hecfaoa.facturacion.domain.service;

import com.hecfaoa.facturacion.domain.model.TipoImpuesto;
import com.hecfaoa.facturacion.domain.ports.EstrategiaImpuesto;

import java.math.BigDecimal;
import java.util.List;

public class CalculadorImpuestoService {

    private final List<EstrategiaImpuesto> estrategias;

    public CalculadorImpuestoService(List<EstrategiaImpuesto> estrategias) {
        this.estrategias = estrategias;
    }

    // Ahora acepta una LISTA de tipos de impuestos
    public BigDecimal calcularImpuestos(List<TipoImpuesto> tiposImpuestos, BigDecimal subtotal) {
        if (tiposImpuestos == null || tiposImpuestos.isEmpty()) {
            return BigDecimal.ZERO;
        }

        // Busca la estrategia para cada tipo solicitado y suma los montos
        return tiposImpuestos.stream()
                .map(tipo -> obtenerEstrategia(tipo).calcular(subtotal))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private EstrategiaImpuesto obtenerEstrategia(TipoImpuesto tipo) {
        return estrategias.stream()
                .filter(e -> e.aplicaPara(tipo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No existe estrategia para: " + tipo));
    }
}