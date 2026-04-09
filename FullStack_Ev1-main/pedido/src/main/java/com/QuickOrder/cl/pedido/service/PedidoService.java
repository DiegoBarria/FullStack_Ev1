package com.QuickOrder.cl.pedido.service;

import com.QuickOrder.cl.pedido.model.Estado;
import com.QuickOrder.cl.pedido.model.Pedido;
import com.QuickOrder.cl.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * El servicio que contiene la logica del negocio para la gestion de pedidos.
 * Ademas de que cordina las operaciones entre el controller y el repository
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    /**
     * retorna la lista completa de todos los pedidos almacenados
     * @return lista de pedidos
     */
    public List <Pedido> getPedidos() {
        return repository.ObtenerTodos();
    }

    /**
     * busca un pedido especifico en base al ID
     * @param id el identificador unico del pedido
     * @return el objeto pedido si se encuentra, o null si este mismo no existe
     */
    public Pedido getPedidoPorId(Long id) {
        return repository.obtenerPorId(id) ;
    }

    /**
     * Registra un nuevo pedido en el sistema.
     * valida los datos y asigna automaticamente las fechas y un ID
     * @param pedido objeto con los datos del nuevo pedido
     * @return el pedido creado o null si los datos son invalidos
     */
    public Pedido savePedido(Pedido pedido) {
        //Validacion manual
        if(pedido.getNombreCliente() == null || pedido.getNombreCliente().trim().isEmpty()) {
            return null;
        }
        if (pedido.getDescripcion() == null || pedido.getDescripcion().trim().isEmpty()) {
            return null;
        }
        if (pedido.getMontoTotal() == null || pedido.getMontoTotal() <= 0) {
            return null;
        }

        //Logica de Negocio
        long nuevoId = repository.ObtenerTodos().size() + 1L;
        pedido.setId(nuevoId);
        pedido.setFechaPedido(LocalDate.now());

        return repository.guardar(pedido);
    }

    /**
     * Actualiza la informacion de un pedido existente
     * @param pedido pedido el objeto pedidio con los datos actualizados
     * @return El pedido actulizado o null si no se encuentra
     */
    public Pedido updatePedido(Pedido pedido) {
        return repository.Actualizar(pedido);
    }

    /**
     * Elimina un pedido del sistema en base a su ID
     * @param id El identificador del pedido a eliminar
     * @return true si se elimino de manera exitisa, o NUll si este no se encunrtra
     */
    public boolean deletePedido(Long id) {
        return repository.eliminar(id);
    }

    /**
     * Filtra la lista de pedidos segun el estado
     * @param estadoBusqueda El estado (ENUM) que se desea filtrar
     * @return Una lista con los pedid que coincidan con el estado
     */
    public List<Pedido> filterPorEstado(Estado estadoBusqueda) {
        List<Pedido> filtrados = new ArrayList<>();
        for (Pedido p: repository.ObtenerTodos()) {
            if (p.getEstado() == estadoBusqueda) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }
}
