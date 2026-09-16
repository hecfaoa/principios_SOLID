package com.hecfaoa.facturacion.infrastructure.config;

import com.hecfaoa.facturacion.domain.ports.EstrategiaImpuesto;
import com.hecfaoa.facturacion.domain.ports.FacturaWriterPort;
import com.hecfaoa.facturacion.domain.ports.ValidadorFacturaPort;
import com.hecfaoa.facturacion.domain.service.CalculadorImpuestoService;
import com.hecfaoa.facturacion.domain.usecase.CrearFacturaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BeanConfiguration {

    @Bean
    public CalculadorImpuestoService calculadorImpuestoService(List<EstrategiaImpuesto> estrategias) {
        return new CalculadorImpuestoService(estrategias);
    }

    @Bean
    public CrearFacturaUseCase crearFacturaUseCase(
            CalculadorImpuestoService calculadorImpuestoService,
            FacturaWriterPort facturaWriterPort,
            List<ValidadorFacturaPort> validadores
    ) {
        return new CrearFacturaUseCase(calculadorImpuestoService, facturaWriterPort, validadores);
    }
}