package com.multiplex.ticketBooking.serviceImpl;

import com.multiplex.ticketBooking.entity.User;
import com.multiplex.ticketBooking.repository.UserRepository;
import com.multiplex.ticketBooking.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    AutoCloseable autoCloseable;
    private User user;
    private User oldUser;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        user = User.builder()
                .userId(1L)
                .userType("user")
                .userName("Ashish")
                .password("Agarwal@12")
                .mobileNumber("9178056821")
                .emailId("agarwal@gmail.com")
                .build();
        oldUser = User.builder()
                .userId(1L)
                .userType("admin")
                .userName("Ashish Agarwal")
                .password("Agarwal@123")
                .mobileNumber("9178056721")
                .emailId("agaral@gmail.com")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @DisplayName("Checking create user")
    void checkingWhetherTheUserHasBeenCreatedTest() {
        mock(User.class);
        mock(UserRepository.class);
        when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.createUser(user)).isEqualTo(user);
    }

    @Test
    @DisplayName("Retrieving a particular user")
    void testRetrieveSingleUserById() {
        mock(User.class);
        mock(UserRepository.class);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        assertThat(userService.getUserById(1L).getUserId()).isEqualTo(user.getUserId());
    }

    @Test
    @DisplayName("Retrieving All Users")
    void testRetrieveAllUsers() {
        mock(User.class);
        mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(new ArrayList<User>(Collections.singleton(user)));
        assertThat(userService.getAllUsers().get(0).getEmailId()).isEqualTo(user.getEmailId());
    }


    @Test
    @DisplayName("Update User")
    void testUpdateUser() {
        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.save(oldUser)).thenReturn(oldUser);
        when(userRepository.findById(1L)).thenReturn(Optional.of(oldUser));
        User updatedUser = userService.updateUserById(user,1L);
        assertEquals("user", updatedUser.getUserType());
        assertEquals("Ashish", updatedUser.getUserName());
        assertEquals("Agarwal@12", updatedUser.getPassword());
        assertEquals("agarwal@gmail.com", updatedUser.getEmailId());
        assertEquals("9178056821", updatedUser.getMobileNumber());
    }

    @Test
    void testDeleteUserById() {
        mock(User.class);
        mock(UserRepository.class);
        doNothing().when(userRepository).deleteById(1L);
        String result = userService.deleteUserById(1L);
        verify(userRepository).deleteById(1L);
        assertEquals("Deleted Successfully",result);
    }
}