package kpfu.itis.util;

import kpfu.itis.model.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextHolder {
    public static UserEntity getUserFromSecurityContext() {
        try {
            return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
            return null;
        }
    }
}
