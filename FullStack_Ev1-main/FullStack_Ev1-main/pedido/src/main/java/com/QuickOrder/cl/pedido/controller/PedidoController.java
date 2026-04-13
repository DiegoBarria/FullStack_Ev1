package com.QuickOrder.cl.pedido.controller;

import com.QuickOrder.cl.pedido.model.Estado;
import com.QuickOrder.cl.pedido.model.Pedido;
import com.QuickOrder.cl.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<List<Pedido>> ListarTodos(){
        return ResponseEntity.ok(service.getPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Pedido pedido = service.getPedidoPorId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }

        Map<String, String> response = new HashMap<>();
        response.put("error", "Pedido no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping
    public ResponseEntity<?> registrarPedido(@RequestBody Pedido pedido) {
        Pedido nuevo = service.savePedido(pedido);

        if (nuevo == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Los datos ingresados son inválidos. Revise que los campos obligatorios no estén vacíos y el monto sea mayor a cero.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido existente = service.getPedidoPorId(id);

        if (existente == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Pedido no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        pedido.setId(id);
        pedido.setFechaPedido(existente.getFechaPedido());
        Pedido actualizado = service.updatePedido(pedido);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {
        boolean eliminado = service.deletePedido(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
    }

        Map<String, String> response = new HashMap<>();
        response.put("error", "Pedido no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pedido>> filtrarPorEstado(@PathVariable Estado estado) {
        return ResponseEntity.ok(service.filterPorEstado(estado));
    }
}
