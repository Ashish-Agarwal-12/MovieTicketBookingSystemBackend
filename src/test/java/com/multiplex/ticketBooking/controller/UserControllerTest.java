package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.User;
import com.multiplex.ticketBooking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;
    User user1;
    User user2;

    List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user1 = User.builder()
                .userId(1L)
                .userType("user")
                .userName("Ashish")
                .password("Agarwal@12")
                .mobileNumber("9178056821")
                .emailId("agarwal@gmail.com")
                .build();
        user2 = User.builder()
                .userId(2L)
                .userType("admin")
                .userName("Ashish Agarwal")
                .password("Agarwal@123")
                .mobileNumber("9178056721")
                .emailId("agaral@gmail.com")
                .build();
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    void createUser() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson = ow.writeValueAsString(user1);
//
//        when(userService.createUser(user1)).thenReturn(user1);
//        this.mockMvc.perform(post("/createUser")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestJson))
//                .andDo(print()).andExpect(status().isOk());
        when(userService.createUser(user1)).thenReturn(user1);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"userId\": 1,\n" +
                        "  \"userName\": \"Ashish\",\n" +
                        "  \"password\": \"Ashish@123\",\n" +
                        "  \"userType\": \"admin\",\n" +
                        "  \"mobileNumber\": \"1234567890\",\n" +
                        "  \"emailId\": \"ag@gmail.com\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAllUser() {
    }

    @Test
    void getUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(user1);
        this.mockMvc.perform(get("/getUserById/" + "1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateUserById() {
    }

    @Test
    void deleteUserById() {
    }
}