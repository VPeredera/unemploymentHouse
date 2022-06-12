package repository;

import domain.Unemployed;
import org.springframework.data.repository.CrudRepository;

public interface UnemployedRepository extends CrudRepository<Unemployed, Integer> {
}
