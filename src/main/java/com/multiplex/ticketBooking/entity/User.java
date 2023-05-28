package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Please Enter a valid UserName")
    @Column(nullable = false, unique = true)
    private String userName;

    @NotBlank(message = "Please Enter a valid Password that should contain AlphaNumeric Numbers and consists of Special Characters")
    @Size(min = 8, max = 25)
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Please mention the userType i.e. User Or Admin")
    private String userType;

    @NotBlank(message = "Please Enter the mobile Number")
    @Size(min = 10)
    @Column(nullable = false)
    private String mobileNumber;

    @Email
    @NotBlank(message = "Please Enter a valid Email")
    @Column(nullable = false, unique = true)
    private String emailId;
}
