package com.example.ideas.admission.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name="admission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admissionId;

    @Column(name = "content")
    @NotBlank(message = "Content of admission information is mandatory")
    private String content;

    @Column(name = "date_of_post")
    private final LocalDate dateOfPost = LocalDate.now();

    //sprawdzić czy to będzie potrzebne
//    @OneToOne(mappedBy = "admission")
//    private final Thread thread;

    public Admission(String content) {
        this.content = content;
    }
}
