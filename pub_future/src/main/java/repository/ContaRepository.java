package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Conta;
import model.TipoConta;

public interface ContaRepository extends JpaRepository<Conta, Long>{	
	
	List<Conta> findByTipoConta(TipoConta tipoConta);

}
