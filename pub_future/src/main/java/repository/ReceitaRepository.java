package repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import model.Receita;
import model.TipoReceita;

//Documentado em ContaRepositor
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

	List<Receita> findByTipoReceita(TipoReceita tipoReceita);

}
