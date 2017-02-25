package main.java.com.vabsssss.repository;

import com.vabsssss.models.TempModel;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */
public interface TempRepository extends CrudRepository<TempModel, Integer> {
    TempModel findByTenantName(String tenantName);
}
