package com.ciandt.api.pedidos.service.serviceImpl;

import com.ciandt.api.pedidos.dto.PedidoDto;
import com.ciandt.api.pedidos.exception.PedidoJaCadastrado;
import com.ciandt.api.pedidos.exception.PedidoNotFoundException;
import com.ciandt.api.pedidos.model.Pedido;
import com.ciandt.api.pedidos.model.Status;
import com.ciandt.api.pedidos.repository.PedidoRepository;
import com.ciandt.api.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    @Override
    public Pedido getPedido(Long id) throws PedidoNotFoundException {
        return repository.findById(id).orElseThrow(PedidoNotFoundException::new);
    }

    @Override
    public List<Pedido> getAllPedido() {
        return repository.findAll();
    }

    @Override
    public void createPedido(PedidoDto pedidoDto) throws PedidoJaCadastrado {
        var pedido = repository.findById(pedidoDto.getId());
        if (pedido.isPresent())
         throw new PedidoJaCadastrado();

        repository.save(pedido.get());
    }

    @Override
    public void updatePedido(Long id, PedidoDto pedidoDto) throws PedidoNotFoundException {
        repository.findById(id).orElseThrow(PedidoNotFoundException::new);

        var pedidoUpdate = Pedido.builder()
                .status(pedidoDto.getStatus())
                .dataHora(pedidoDto.getDataHora())
                .id(id)
                .itens(pedidoDto.getItens())
                .build();

        repository.save(pedidoUpdate);
    }

    @Override
    public void updateStatusPedido(Long id, Status status) throws PedidoNotFoundException {
        var pedidoExist = repository.findById(id).orElseThrow(PedidoNotFoundException::new);

        var pedido = Pedido.builder()
                .status(status)
                .dataHora(pedidoExist.getDataHora())
                .id(id)
                .itens(pedidoExist.getItens())
                .build();

        repository.save(pedido);
    }
}
