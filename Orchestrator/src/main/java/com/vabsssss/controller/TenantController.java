package com.vabsssss.controller;

import com.vabsssss.models.Tenant;
import com.vabsssss.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by vaibhav.janardhan on 25/01/17.
 */
@RestController
@RequestMapping("/tenants")
public class TenantController {
    @Autowired
    TenantRepository tenantRepository;

    /*@Autowired
    BookmarkRestController(BookmarkRepository bookmarkRepository,
                           AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }*/

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Tenant>> getAllTenants() {
        return new ResponseEntity<>((Collection<Tenant>) tenantRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public ResponseEntity<Tenant> getTenantById(@PathVariable int id) {
        this.validateTenant(id);
        return new ResponseEntity<>(tenantRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<Tenant> findPieWithName(@RequestParam(value="name") String name) {
        this.validateTenant(name);
        return new ResponseEntity<>(tenantRepository.findByTenantName(name),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addTenant(Tenant input) {
        return new ResponseEntity<>(tenantRepository.save(input), HttpStatus.CREATED);
    }

    private void validateTenant(String tenantName) {
        //this.tenantRepository.findByTenantName(tenantName).orElseThrow(() -> new TenantNotFoundException(tenantName));
    }
    private void validateTenant(Integer id) {
        //this.tenantRepository.findByTenantName(tenantName).orElseThrow(() -> new TenantNotFoundException(tenantName));
    }
}
