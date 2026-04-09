package com.QuickOrder.cl.pedido.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class Pedido {
    private Long id;

    @NotBlank (message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotBlank (message = "La descripcionn es obligatoria")
    private String descripcion;

    @NotNull (message = "El estado es obligatorio")
    private Estado estado;

    @NotNull (message = "El tipo de pedido es Obligatorio")
    private TipoPedido tipoPedido;

    @NotNull (message = "El monto total debe ser obligatorio")
    @Positive (message = "El monto total debe ser mayor que cero")
    private Double montoTotal;

    private LocalDate fechaPedido;
}