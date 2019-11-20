package br.com.thiagoGomes.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.thiagoGomes.domain.Endereco;
import br.com.thiagoGomes.domain.ItemPedido;
import br.com.thiagoGomes.domain.Pagamento;
import br.com.thiagoGomes.domain.Pedido;

public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime instante;
	private Pagamento pagamento;
	private ClientePedidoDTO cliente;
	private Endereco enderecoDeEntrega;
	private Set<ItemPedido> itens = new HashSet<>();
	
	

	public PedidoDTO(Pedido pedido) {
		id = pedido.getId();
		instante = pedido.getInstante();
		pagamento = pedido.getPagamento();
		cliente = new ClientePedidoDTO(pedido.getCliente());
		enderecoDeEntrega = pedido.getEnderecoDeEntrega();
		itens = pedido.getItens();
	}
	
    public double getValorTotal() {
    	return itens.stream().mapToDouble(x -> x.getSubTotal()).sum();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public void setInstante(LocalDateTime instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public ClientePedidoDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClientePedidoDTO cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

}
