package com.ciandt.api.pedidos.service.serviceImpl;

import com.ciandt.api.pedidos.model.Pedido;
import com.ciandt.api.pedidos.repository.PedidoRepository;
import com.ciandt.api.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    //TODO: implement exceptions
    private final PedidoRepository repository;
    @Override
    public Pedido getPedido(Long id) {
        var pedidoExist = repository.findById(id);
        if (pedidoExist.isEmpty())
            throw new RuntimeException();
        return pedidoExist.get();
    }

    @Override
    public List<Pedido> getAllPedido() {
        return repository.findAll();
    }

    @Override
    public Pedido createPedido(Pedido pedido) {
        var pedidoExist = repository.findById(pedido.getId());
        if (pedidoExist.isPresent())
            throw new RuntimeException();
        return repository.save(pedido);
    }

    @Override
    public Pedido updatePedido(Long id, Pedido pedido) {
        var pedidoExist = repository.findById(id);
        if (pedidoExist.isEmpty())
            throw new RuntimeException();
        return repository.save(pedido);
    }
}
