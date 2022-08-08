package com.ciandt.api.pagamento.dto;

import com.ciandt.api.pagamento.model.FormaDePagamento;
import com.ciandt.api.pagamento.model.Status;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Builder
@Data
public class PagamentoDto {

    private Long id;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Max(100)
    private String nome;

    @NotBlank
    @Max(100)
    private String numero;

    private String expiracao;

    @NotBlank
    @Size(min = 3, max = 3)
    private String codigo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    private Long pedidoId;

    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;
}
