package br.com.thiagoGomes.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import br.com.thiagoGomes.domain.enums.EstadoPagamento;

@Entity
//Comentei a linha abaixo, pois configurei com a anotação @JsonSubTypes na classe Pai
//@JsonTypeName("pagamentoComCartao") //Serve pra mapear o @JsonTypeInfo da classe Pagamento
public class PagamentoComCartao extends Pagamento implements Serializable {

    private static final long serialVersionUID = -2622391275401872569L;
    private Integer numeroDeParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }


}
