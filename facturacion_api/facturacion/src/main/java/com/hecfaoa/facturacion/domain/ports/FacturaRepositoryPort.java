package com.hecfaoa.facturacion.domain.ports;

import com.hecfaoa.facturacion.domain.model.Factura;
import java.util.Optional;
import java.util.UUID;

/**
 * DIP (Dependency Inversion Principle):
 * Esta interfaz es un PUERTO de entrada/salida. El dominio define qué necesita
 * (guardar y buscar), pero la implementación técnica vivirá en la infraestructura.
 */
public interface FacturaRepositoryPort {
    
    Factura guardar(Factura factura);
    
    Optional<Factura> buscarPorId(UUID id);
}
