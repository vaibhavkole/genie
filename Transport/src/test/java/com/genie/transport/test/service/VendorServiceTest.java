package com.genie.transport.test.service;

import com.genie.transport.model.Vendor;
import com.genie.transport.repository.IVendorRepository;
import com.genie.transport.service.IVendorService;
import com.genie.transport.service.VendorService;
import com.genie.transport.test.AbstractTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
public class VendorServiceTest {

    public static class CreateVendor extends AbstractTest {

        @Mock
        IVendorRepository repository;

        IVendorService service;

        @Before
        public void setUp() {
            service = new VendorService(repository);
        }

        @Test
        public void checkNullEntries() {
            String name = null;
            String address = "ABC";
            try {
                service.createVendor(name, address);
            } catch (NullPointerException e) {
                Assert.assertNotNull(e);
            }
        }

        @Test
        public void normalCase() {
            String name = "CDE";
            String address = "ABC";
            Vendor vendor = new Vendor();
            when(repository.save(any(Vendor.class))).thenReturn(vendor);
            service.createVendor(name, address);

        }






    }

}
