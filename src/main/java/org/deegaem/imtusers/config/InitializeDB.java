package org.deegaem.imtusers.config;

import org.deegaem.imtusers.domain.Credential;
import org.deegaem.imtusers.domain.User;
import org.deegaem.imtusers.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeDB {

    private static final Logger log = LoggerFactory.getLogger(InitializeDB.class);

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostConstruct
    public void init() {

        log.debug("Db initialized");

        //Dummy accounts
        User adminUser = new User();
        adminUser.setCredential(new Credential("admin", "admin"));
        adminUser.setEmail("admin@deegaem.org");
        adminUser.setName("Admin");
        userServiceImpl.insert(adminUser);

        User guestUser = new User();
        guestUser.setCredential(new Credential("guest", "guest"));
        guestUser.setEmail("guest@deegaem.org");
        guestUser.setName("Guest");
        userServiceImpl.insert(guestUser);

    }
}

