package br.com.thiagoGomes.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.thiagoGomes.domain.enums.Perfil;
import br.com.thiagoGomes.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = -8534252972648493078L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    
    @Column(unique = true)
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;
    
    @JsonIgnore
    private String senha;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) //Em caso de deleção, é deletado o endereços tambem
    private List<Endereco> enderecos = new ArrayList<>();
    @ElementCollection //Cria uma tabela de entidade fraca
    @CollectionTable(name ="TELEFONE")
    private Set<String> telefones = new HashSet<>();
    
    @ElementCollection(fetch = FetchType.EAGER) //Cria uma tabela de entidade fraca
    @CollectionTable(name ="PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    @JsonIgnore //Evita o erro de concorrencia ciclica, erro "Expected ',' instead of 't'"
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
    	addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, String senha) {
    	addPerfil(Perfil.CLIENTE);
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo==null) ? null : tipo.getCod();
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public String getTipo() {
        return TipoCliente.toEnum(tipo).getDescricao();
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCod();
    }

    public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    //Devolve o perfil completo
    public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

    //Salva apenas o cod do perfil no banco
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
