package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Despesa;
import model.TipoDespesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

	List<Despesa> findByTipoDespesa(TipoDespesa tipoDespesa);
}
