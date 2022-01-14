package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Conta;
import model.TipoConta;

//Classe repository ultilizada para fazer operacoes com o banco de dados que herda a inteface do JPA do Spring boot
public interface ContaRepository extends JpaRepository<Conta, Long>{	
	
	List<Conta> findByTipoConta(TipoConta tipoConta);

}
