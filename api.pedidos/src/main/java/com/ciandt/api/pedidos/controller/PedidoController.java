package com.ciandt.api.pedidos.controller;

import com.ciandt.api.pedidos.model.Pedido;
import com.ciandt.api.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    @GetMapping("/{id}")
    public Pedido getPedido (@PathVariable Long id){
        return service.getPedido(id);
    }

    @GetMapping("/allPedidos")
    public List<Pedido> getAllPedido (){
        return service.getAllPedido();
    }

    @PostMapping
    public void createPedido(@RequestBody Pedido pedido) {
        service.createPedido(pedido);
    }

    @PutMapping("/{id}")
    public void updatePedido(@PathVariable Long id, @RequestBody Pedido pedido){
        service.updatePedido(id,pedido);
    }
}
