package com.hecfaoa.facturacion.infrastructure.adapter.persistence;

import com.hecfaoa.facturacion.domain.model.Factura;
import com.hecfaoa.facturacion.domain.ports.FacturaReaderPort;
import com.hecfaoa.facturacion.domain.ports.FacturaWriterPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class FacturaRepositoryAdapter implements FacturaWriterPort, FacturaReaderPort {

    private final SpringDataFacturaRepository springDataRepository;

    public FacturaRepositoryAdapter(SpringDataFacturaRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Factura guardar(Factura factura) {
        FacturaEntity entity = new FacturaEntity(
                factura.getId(),
                factura.getClienteId(),
                factura.getFechaEmision(),
                factura.getSubtotal(),
                factura.getImpuestos(),
                factura.getTotal()
        );
        springDataRepository.save(entity);
        return factura;
    }

    @Override
    public Optional<Factura> buscarPorId(UUID id) {
        return springDataRepository.findById(id)
                .map(entity -> {
                    // Mapeo inverso de Entity a Factura de Dominio
                    Factura factura = new Factura(entity.getClienteId(), java.util.Collections.emptyList());
                    factura.aplicarImpuestos(entity.getImpuestos());
                    return factura;
                });
    }
}