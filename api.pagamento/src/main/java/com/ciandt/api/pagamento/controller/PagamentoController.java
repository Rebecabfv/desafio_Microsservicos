package com.ciandt.api.pagamento.controller;

import com.ciandt.api.pagamento.dto.PagamentoDto;
import com.ciandt.api.pagamento.exception.PagamentoNotFoundException;
import com.ciandt.api.pagamento.model.Pagamento;
import com.ciandt.api.pagamento.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/pagamento")
@RestController
public class PagamentoController {

    private final PagamentoService service;

    @GetMapping("/{id}")
    public Pagamento getPagamento(@PathVariable Long id) throws PagamentoNotFoundException {
        return service.getPagamento(id);
    }

    @GetMapping
    public List<Pagamento> getAllPagamentos(){
        return service.getAllPagamentos();
    }

    @PostMapping
    public void savePagamento(@RequestBody PagamentoDto pagamentoDto){
        service.savePagamento(pagamentoDto);
    }

    @PutMapping("/{id}")
    public void updatePagamento(@PathVariable Long id, @RequestBody PagamentoDto pagamentoDto){
        service.updatePagamento(id, pagamentoDto);
    }

    @DeleteMapping("/{id}")
    public void deletePagamento(@PathVariable Long id) throws PagamentoNotFoundException {
        service.deletePagamento(id);
    }

}
