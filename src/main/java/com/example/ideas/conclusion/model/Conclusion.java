package com.example.ideas.conclusion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="conclusion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conclusion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conclusionId;

    @Column(name = "content")
    @NotBlank(message = "Content of conclusion information is mandatory")
    private String content;

    @Column(name = "conclusion_points")
    private Integer points;

    @Column(name = "date_of_post")
    private final LocalDate dateOfPost = LocalDate.now();

    public Conclusion(String content, Integer points) {

        this.content = content;
        this.points = points;
    }
}
