package com.business.repository;

import com.business.models.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */
@Repository
public interface MerchantRepository extends CrudRepository<Merchant, Integer> {
}
