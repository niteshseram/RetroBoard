package com.nitesh.retroboard.service;

import com.nitesh.retroboard.model.User;
import com.nitesh.retroboard.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void getAllCommentsForToday_HappyPath_ShouldReturn1Comment()
    {
        // Given
        User user = new User();
        user.setUsername("nitesh");
        user.setPassword("sha908");
        user.setRole("USER");
        when(userRepository.findByUsername("nitesh")).thenReturn(user);
        // When
        UserDetails actual = userService.loadUserByUsername("nitesh");
        // Then
        verify(userRepository, times(1)).findByUsername("nitesh");
    }

}
