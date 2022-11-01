package pl.kurs.shapecreatorapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.model.security.AppRole;
import pl.kurs.shapecreatorapp.model.security.AppUser;
import pl.kurs.shapecreatorapp.repository.AppUserRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        AppRole adminRole = new AppRole("ROLE_ADMIN");
        AppRole creatorRole = new AppRole("ROLE_CREATOR");

        AppUser admin = new AppUser("admin", passwordEncoder.encode("admin"), Set.of(adminRole));
        AppUser creator = new AppUser("creator", passwordEncoder.encode("creator"), Set.of(creatorRole));

        appUserRepository.saveAll(List.of(admin, creator));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return appUserRepository.findByUsernameWithRoles(s)
                .orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
