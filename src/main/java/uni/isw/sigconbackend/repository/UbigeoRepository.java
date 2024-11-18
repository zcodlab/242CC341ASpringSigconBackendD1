package uni.isw.sigconbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uni.isw.sigconbackend.model.Ubigeo;

@Repository
public interface UbigeoRepository extends CrudRepository<Ubigeo,String>{
    
}
