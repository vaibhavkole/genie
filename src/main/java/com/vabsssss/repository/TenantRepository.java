package com.vabsssss.repository;

import com.vabsssss.models.Tenant;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vaibhav.janardhan on 25/01/17.
 */
public interface TenantRepository extends CrudRepository<Tenant, Integer> {
    Tenant findByTenantName(String tenantName);
}
