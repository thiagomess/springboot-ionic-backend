package br.com.thiagoGomes.domain;

import br.com.thiagoGomes.domain.enums.EstadoPagamento;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)  // Gera uma tabela pra cada classe que herda desta classe.
public abstract class Pagamento implements Serializable {

    private static final long serialVersionUID = 1400970556031056608L;
    @Id
    private Integer id;
    private Integer estado;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name="pedido_id")
    @MapsId  //Recebe como id o id do Pedido
    private Pedido pedido;


    public Pagamento() {
    }

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado.getCod();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return id.equals(pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
