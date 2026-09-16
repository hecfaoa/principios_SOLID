package com.hecfaoa.facturacion.infrastructure.adapter.rest;

import com.hecfaoa.facturacion.domain.model.Factura;
import com.hecfaoa.facturacion.domain.model.LineaFactura;
import com.hecfaoa.facturacion.domain.usecase.CrearFacturaUseCase;
import com.hecfaoa.facturacion.infrastructure.adapter.rest.dto.CrearFacturaRequest;
import com.hecfaoa.facturacion.infrastructure.adapter.rest.dto.FacturaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/facturas")
@CrossOrigin(origins = "*")
public class FacturaController {

    private final CrearFacturaUseCase crearFacturaUseCase;

    public FacturaController(CrearFacturaUseCase crearFacturaUseCase) {
        this.crearFacturaUseCase = crearFacturaUseCase;
    }

    @PostMapping
    public ResponseEntity<FacturaResponse> crearFactura(@RequestBody CrearFacturaRequest request) {
        List<LineaFactura> lineasDominio = request.lineas().stream()
                .map(l -> new LineaFactura(
                        l.codigoProducto(),
                        l.descripcion(),
                        l.cantidad(),
                        l.precioUnitario()
                ))
                .toList();

        // Se usa request.tiposImpuestos() (en plural)
        Factura facturaCreada = crearFacturaUseCase.ejecutar(
                request.clienteId(),
                lineasDominio,
                request.tiposImpuestos()
        );

        FacturaResponse response = new FacturaResponse(
                facturaCreada.getId(),
                facturaCreada.getClienteId(),
                facturaCreada.getFechaEmision(),
                facturaCreada.getSubtotal(),
                facturaCreada.getImpuestos(),
                facturaCreada.getTotal()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}