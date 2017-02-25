package com.vabsssss.cont;

import com.vabsssss.models.TempModel;
import com.vabsssss.repository.TempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */
@RestController
@RequestMapping("/tenants")
public class tempController {
    @Autowired
    TempRepository tempRepository;

    /*@Autowired
    BookmarkRestController(BookmarkRepository bookmarkRepository,
                           AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }*/

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<TempModel>> getAllTenants() {
        return new ResponseEntity<>((Collection<TempModel>) tempRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public ResponseEntity<TempModel> getTenantById(@PathVariable int id) {
        this.validateTenant(id);
        return new ResponseEntity<>(tempRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<TempModel> findPieWithName(@RequestParam(value="name") String name) {
        this.validateTenant(name);
        return new ResponseEntity<>(tempRepository.findByTenantName(name),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addTenant(TempModel input) {
        return new ResponseEntity<>(tempRepository.save(input), HttpStatus.CREATED);
    }

    private void validateTenant(String tenantName) {
        //this.tempRepository.findByTenantName(tenantName).orElseThrow(() -> new TenantNotFoundException(tenantName));
    }
    private void validateTenant(Integer id) {
        //this.tempRepository.findByTenantName(tenantName).orElseThrow(() -> new TenantNotFoundException(tenantName));
    }
}
