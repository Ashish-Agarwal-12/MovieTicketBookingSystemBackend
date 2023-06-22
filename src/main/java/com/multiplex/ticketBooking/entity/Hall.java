package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hallId;

    @Column(unique = true)
    @NotEmpty(message = "Please Enter the hall Name")
    @Length(min = 3,message = "The Name of the Hall should be unique")
    private String hallName;

    @NotEmpty(message = "Please Enter address of the Hall")
    private String address;

    @NotNull(message = "It should not be zero")
    @Min(value = 60, message = "Minimum Capacity Should be 60")
    private Integer totalCapacity;
}
