package com.bmsmart.controllers.local;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InitController {


    private static final Logger log = LoggerFactory.getLogger(InitController.class);

    /**
     * Login等处理
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity executeRuleService(String userId, String password) throws IOException {

        Subject currentUser = SecurityUtils.getSubject();

        List<String> rolesList = new ArrayList<String>();

        rolesList.add("admin");
        rolesList.add("user1");
        currentUser.hasRole("user1");
        currentUser.hasRoles(rolesList);

        (new Subject.Builder()).buildSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userId, password);


        HttpStatus STATUS = HttpStatus.OK;
        try {
            currentUser.login(usernamePasswordToken);

        } catch (UnknownAccountException uae) {

            log.warn(uae.toString());
            STATUS = HttpStatus.NOT_ACCEPTABLE;

        } catch (IncorrectCredentialsException ice) {

            log.warn(ice.toString());
            STATUS = HttpStatus.NOT_ACCEPTABLE;

        } catch (LockedAccountException lae) {
            log.warn(lae.toString());
            STATUS = HttpStatus.FORBIDDEN;

        } catch (ExcessiveAttemptsException eae) {
            log.warn(eae.toString());
            STATUS = HttpStatus.UNAUTHORIZED;
        } catch (AuthenticationException ae) {

            log.warn(ae.toString());
            STATUS = HttpStatus.UNAUTHORIZED;


        }

        return new ResponseEntity("modelList", STATUS);

    }
}
