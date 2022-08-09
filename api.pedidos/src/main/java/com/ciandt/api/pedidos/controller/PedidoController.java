package com.ciandt.api.pedidos.controller;

import com.ciandt.api.pedidos.dto.PedidoDto;
import com.ciandt.api.pedidos.exception.PedidoJaCadastrado;
import com.ciandt.api.pedidos.exception.PedidoNotFoundException;
import com.ciandt.api.pedidos.model.Pedido;
import com.ciandt.api.pedidos.model.Status;
import com.ciandt.api.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    @GetMapping(path = "/{id}", produces = "application/json")
    public Pedido getPedido (@PathVariable Long id) throws PedidoNotFoundException {
        return service.getPedido(id);
    }

    @GetMapping(path = "/allPedidos", produces = "application/json")
    public List<Pedido> getAllPedido (){
        return service.getAllPedido();
    }

    @PostMapping (produces = "application/json")
    public void createPedido(@RequestBody PedidoDto pedidoDto) throws PedidoJaCadastrado {
        service.createPedido(pedidoDto);
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public void updatePedido(@PathVariable Long id, @RequestBody PedidoDto pedidoDto) throws PedidoNotFoundException {
        service.updatePedido(id,pedidoDto);
    }

    @PutMapping(path = "/{id}/{status}", produces = "application/json")
    public void updateStatusPedido(@PathVariable Long id, @PathVariable Status status) throws PedidoNotFoundException {
        service.updateStatusPedido(id,status);
    }
}
