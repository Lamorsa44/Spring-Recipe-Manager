package recipes.business;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import recipes.model.YiaUser;

import java.util.Collection;
import java.util.List;

public class YiaUserAdapter implements UserDetails {

    private final YiaUser yiaUser;

    public YiaUserAdapter(YiaUser yiaUser) {
        this.yiaUser = yiaUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return yiaUser.getPassword();
    }

    @Override
    public String getUsername() {
        return yiaUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
