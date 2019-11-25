package br.com.thiagoGomes.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.thiagoGomes.domain.enums.EstadoPagamento;

@Entity
//Comentei a linha abaixo, pois configurei com a anotação @JsonSubTypes na classe Pai
//@JsonTypeName("pagamentoComBoleto") //Serve pra mapear o @JsonTypeInfo da classe Pagamento 
public class PagamentoComBoleto extends Pagamento implements Serializable {

    private static final long serialVersionUID = 7297328117652387761L;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPagamento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, LocalDate dataVencimento, Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
