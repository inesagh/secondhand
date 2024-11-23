package com.example.secondhandclothes.user.service;

import com.example.secondhandclothes.user.rest.models.UserRequestDto;
import com.example.secondhandclothes.user.rest.models.UserResponseDto;
import com.example.secondhandclothes.user.domain.User;
import com.example.secondhandclothes.user.domain.UserRepository;
import com.example.secondhandclothes.util.exception.AppException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void loadUserByUsernameTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("password");
        user.setRoles("ROLES_USER");

        Mockito.when(userRepository.findByUsername("test")).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername("test");

        assertNotNull(userDetails);
        assertEquals("test", userDetails.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUnknownUserByUsernameTest() {
        Mockito.when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        userService.loadUserByUsername("unknown");
    }

    @Test
    public void createUserTest() {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setUsername("test");
        requestDto.setPassword("password");

        User userToBeSaved = new User();
        userToBeSaved.setUsername("test");
        userToBeSaved.setPassword(passwordEncoder.encode("password"));

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("test");
        savedUser.setPassword(userToBeSaved.getPassword());

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(savedUser);
        Mockito.when(passwordEncoder.encode("password")).thenReturn(userToBeSaved.getPassword());

        UserResponseDto responseDto = userService.create(requestDto);

        assertNotNull(responseDto);
        assertEquals("test", responseDto.getUsername());
    }

    @Test
    public void getUsersTest() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");

        Mockito.when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<UserResponseDto> users = userService.get();

        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("user1", users.get(0).getUsername());
        assertEquals("user2", users.get(1).getUsername());
    }

    @Test
    public void getAuthenticatedUserTest() throws AppException {
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("test");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        authenticatedUser.setUsername("test");

        Mockito.when(userRepository.findByUsername("test")).thenReturn(Optional.of(authenticatedUser));

        User user = userService.getAuthenticatedUser();

        assertNotNull(user);
        assertEquals("test", user.getUsername());
    }

    @Test(expected = AppException.class)
    public void getNotFounAuthenticatedUserTest() throws AppException {
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("unknown");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        userService.getAuthenticatedUser();
    }
}
