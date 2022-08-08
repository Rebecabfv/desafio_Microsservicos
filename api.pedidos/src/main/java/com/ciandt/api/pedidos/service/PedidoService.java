package com.ciandt.api.pedidos.service;

import com.ciandt.api.pedidos.dto.PedidoDto;
import com.ciandt.api.pedidos.exception.PedidoJaCadastrado;
import com.ciandt.api.pedidos.exception.PedidoNotFoundException;
import com.ciandt.api.pedidos.model.Pedido;
import com.ciandt.api.pedidos.model.Status;

import java.util.List;

public interface PedidoService {
    Pedido getPedido(Long id) throws PedidoNotFoundException;

    List<Pedido> getAllPedido();

    void createPedido(PedidoDto pedidoDto) throws PedidoJaCadastrado;

    void updatePedido(Long id, PedidoDto pedido) throws PedidoNotFoundException;

    void updateStatusPedido(Long id, Status status) throws PedidoNotFoundException;
}
