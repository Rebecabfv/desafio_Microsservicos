package com.ciandt.api.pedidos.service;

import com.ciandt.api.pedidos.model.Pedido;

import java.util.List;

public interface PedidoService {
    Pedido getPedido(Long id);

    List<Pedido> getAllPedido();

    Pedido createPedido(Pedido pedido);

    Pedido updatePedido(Long id, Pedido pedido);
}
