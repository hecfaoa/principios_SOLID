package com.hecfaoa.facturacion.infrastructure.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SpringDataFacturaRepository extends JpaRepository<FacturaEntity, UUID> {
    // Spring Data genera automáticamente la implementación de guardar, buscar por ID,
    //  etc.
}
