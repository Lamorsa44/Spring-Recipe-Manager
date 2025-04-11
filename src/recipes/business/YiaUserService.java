package recipes.business;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.business.DTO.RegisterRequest;
import recipes.business.exception.YeaBadRequestException;
import recipes.business.exception.YeaNotFoundException;
import recipes.model.YiaUser;
import recipes.repository.YiaUserRepository;

@Service
public class YiaUserService implements UserDetailsService {

    private final YiaUserRepository yiaUserRepository;
    private final PasswordEncoder passwordEncoder;


    public YiaUserService(YiaUserRepository yiaUserRepository, PasswordEncoder passwordEncoder) {
        this.yiaUserRepository = yiaUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(RegisterRequest request) {
        if (yiaUserRepository.existsByEmail(request.email())) {
            throw new YeaBadRequestException("Email already in use");
        }

        yiaUserRepository.save(new YiaUser(request.email(), passwordEncoder.encode(request.password())));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new YiaUserAdapter(yiaUserRepository.findByEmail(username)
                .orElseThrow(YeaNotFoundException::new));
    }
}
