package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @NotBlank(message = "Please Enter a hall Name")
    private String hallName;

    @NotBlank(message = "Please Enter address of the Hall")
    private String address;

    @Column(nullable = false)
    private Integer totalCapacity;
}
