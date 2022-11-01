package pl.kurs.shapecreatorapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.kurs.shapecreatorapp.model.security.AppRole;
import pl.kurs.shapecreatorapp.model.security.AppUser;
import pl.kurs.shapecreatorapp.repository.AppUserRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private AppUserService appUserService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        appUserService = new AppUserService(appUserRepository, passwordEncoder);
    }

    @Test
    void shouldReturnUserByUsername() {

        AppRole creatorRole = new AppRole("ROLE_CREATOR");
        AppUser creator = new AppUser("creator", passwordEncoder.encode("creator"), Set.of(creatorRole));
        List<AppUser> appUsers = List.of(creator);

        Mockito.when(appUserRepository.saveAll(List.of(creator))).thenReturn(appUsers);

        UserDetails result = appUserService.loadUserByUsername("creator");
        assertEquals(result, creator);
    }

}