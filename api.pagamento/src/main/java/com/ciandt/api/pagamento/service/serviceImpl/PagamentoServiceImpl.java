package com.ciandt.api.pagamento.service.serviceImpl;

import com.ciandt.api.pagamento.dto.PagamentoDto;
import com.ciandt.api.pagamento.exception.PagamentoNotFoundException;
import com.ciandt.api.pagamento.model.Pagamento;
import com.ciandt.api.pagamento.model.Status;
import com.ciandt.api.pagamento.repository.PagamentoRepository;
import com.ciandt.api.pagamento.service.PagamentoService;
import com.ciandt.api.pagamento.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@RequiredArgsConstructor
@Service
public class PagamentoServiceImpl implements PagamentoService {

    private final PedidoService pedidoService;

    private final PagamentoRepository repository;
    
    @Override
    public Pagamento getPagamento(Long id) throws PagamentoNotFoundException {

        checkArgument(id > 0, "Id precisa ser maior que zero");

        return repository.findById(id).orElseThrow(() -> new PagamentoNotFoundException("Pagamento nao encontrado"));
    }

    @Override
    public void savePagamento(PagamentoDto pagamentoDto) {
        checkNotNull(pagamentoDto, "Pagamento nao pode ser null");

        final Pagamento pagamento = Pagamento.builder()
                .nome(pagamentoDto.getNome())
                .formaDePagamento(pagamentoDto.getFormaDePagamento())
                .codigo(pagamentoDto.getCodigo())
                .valor(pagamentoDto.getValor())
                .id(pagamentoDto.getId())
                .numero(pagamentoDto.getNumero())
                .expiracao(pagamentoDto.getExpiracao())
                .status(pagamentoDto.getStatus())
                .pedidoId(pagamentoDto.getPedidoId())
                .build();

        repository.save(pagamento);

        updateStatusPedido(pagamento);
    }

    private void updateStatusPedido(Pagamento pagamento) {
        checkNotNull(pagamento.getStatus(), "Status nao pode ser null");
        if (Status.CONFIRMADO.equals(pagamento.getStatus()))
            pedidoService.modificarStatusPedido(pagamento.getPedidoId(), pagamento.getStatus());
    }

    @Override
    public void updatePagamento(Long id, PagamentoDto pagamentoDto) throws PagamentoNotFoundException {
        checkArgument(id > 0, "Id precisa ser maior que zero");
        checkNotNull(pagamentoDto, "Pagamento nao pode ser null");

        final var pagamentoExiste = repository.findById(id).orElseThrow(() -> new PagamentoNotFoundException("Pagamento nao encontrado"));
        
        final Pagamento pagamento = pagamentoExiste.builder()
                .nome(pagamentoDto.getNome())
                .formaDePagamento(pagamentoDto.getFormaDePagamento())
                .codigo(pagamentoDto.getCodigo())
                .valor(pagamentoDto.getValor())
                .id(pagamentoDto.getId())
                .numero(pagamentoDto.getNumero())
                .expiracao(pagamentoDto.getExpiracao())
                .status(pagamentoDto.getStatus())
                .pedidoId(pagamentoDto.getPedidoId())
                .build();

        repository.save(pagamento);

        updateStatusPedido(pagamento);
    }

    @Override
    public void deletePagamento(Long id) throws PagamentoNotFoundException {
        checkArgument(id > 0, "Id precisa ser maior que zero");

        final var pagamento = repository.findById(id).orElseThrow(() -> new PagamentoNotFoundException("Pagamento nao encontrado"));

        repository.delete(pagamento);
    }

    @Override
    public List<Pagamento> getAllPagamentos() {
        return repository.findAll();
    }
}
