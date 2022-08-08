package com.ciandt.api.pagamento.service;

import com.ciandt.api.pagamento.dto.PagamentoDto;
import com.ciandt.api.pagamento.exception.PagamentoNotFoundException;
import com.ciandt.api.pagamento.model.Pagamento;

import java.util.List;

public interface PagamentoService {
    Pagamento getPagamento(Long id) throws PagamentoNotFoundException;

    void savePagamento(PagamentoDto pagamentoDto);

    void updatePagamento(Long id, PagamentoDto pagamentoDto) throws PagamentoNotFoundException;

    void deletePagamento(Long id) throws PagamentoNotFoundException;

    List<Pagamento> getAllPagamentos();
}
