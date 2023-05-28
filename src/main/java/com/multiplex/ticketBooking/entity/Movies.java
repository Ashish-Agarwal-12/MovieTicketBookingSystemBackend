package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @NotBlank(message = "Please Enter the title of the movie")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Please Enter the description of the movie")
    @Column(nullable = false)
    private String description;

    @NotBlank(message = "Please Enter the genre of the movie")
    @Column(nullable = false)
    private String genre;

    @NotBlank(message = "Please Enter the duration of the movie in Minutes")
    @Size(min = 90, max = 210)
    @Column(nullable = false)
    private Integer duration;

    @NotBlank(message = "Please Enter the Movie Release Date")
    @Future
    @Column(nullable = false)
    private String releaseDate;

    @NotBlank(message = "Please Enter slot numbers")
    @Size(min = 1, max = 8)
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Slot.class)
    @JoinColumn(referencedColumnName = "movieId")
    private List<Slot> slots;
}
