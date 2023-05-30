package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String title;

    private String description;


    private String genre;

    private Integer duration;

    private LocalDate releaseDate;

    @Size(min = 1, max = 6)
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Slot.class)
    @JoinColumn(referencedColumnName = "movieId")
    private List<Slot> slots;
}
