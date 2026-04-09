package com.QuickOrder.cl.pedido.repository;

import com.QuickOrder.cl.pedido.model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepository {

    private List<Pedido> ListaPedidos = new ArrayList<>();

    public List<Pedido> ObtenerTodos() {
        return ListaPedidos;
    }

    public Pedido obtenerPorId(Long id) {
        for (Pedido p : ListaPedidos) {
            if (p.getId().equals(id)) return p;
            }
            return null;
        }
        public Pedido guardar(Pedido pedido) {
        ListaPedidos.add(pedido);
        return pedido;
        }

        public Pedido Actualizar(Pedido pedidoActualizado) {
        for (int i = 0; i < ListaPedidos.size(); i++) {
            if (ListaPedidos.get(i).getId().equals(pedidoActualizado.getId())) {
                ListaPedidos.set(i, pedidoActualizado);
                return pedidoActualizado;
            }
        }
        return null;
    }

    public boolean eliminar(Long id) {
        for (int i = 0; i < ListaPedidos.size(); i++) {
            if (ListaPedidos.get(i).getId().equals(id)) {
                ListaPedidos.remove(i);
                return true;
            }
        }
        return false;
    }
}

