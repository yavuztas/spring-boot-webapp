package dev.yavuztas.boilerplate.springbootwebapp.domain;

/**
 * Constants for most used user roles. Also it converts {@link Role} domain to role names that match Spring Security's "ROLE_" definitions
 *
 * @author Yavuz Tas
 */
public class RoleType {

    public static final String ADMIN = "ROLE_Admin";
    public static final String GUEST = "ROLE_Guest";

    public static String getRole(Role role){
        return "ROLE_"+role.getRoleDescription();
    }


}
