package com.vabsssss.repository;

import com.vabsssss.models.Hub;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by heena.h on 26/02/17.
 */
public interface HubRepository extends CrudRepository<Hub, Integer> {
//    TempModel findByTenantName(String tenantName);
}
