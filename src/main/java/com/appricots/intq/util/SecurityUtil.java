package com.appricots.intq.util;

import com.appricots.intq.model.User;
import com.appricots.intq.wrappers.IntqUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    public static Optional<User> getCurrentUser() {
        IntqUserDetails intqUserDetails = (IntqUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (intqUserDetails != null) {
            logger.debug("Current user: {}", intqUserDetails.getUser());
            return Optional.ofNullable(intqUserDetails.getUser());
        } else {
            logger.debug("No user found");
            return Optional.empty();
        }
    }
}
