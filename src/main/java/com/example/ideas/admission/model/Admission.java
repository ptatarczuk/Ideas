package com.example.ideas.admission.model;

import com.example.ideas.thread.model.Thread;
import com.example.ideas.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name="admissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admissionId;

    @Column(name = "admission_text")
    @NotBlank(message = "Content of admission information is mandatory")
    private String content;

    @Column(name = "admission_date")
    private final LocalDate dateOfPost = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "admission_author")
    private User user;

    @OneToOne
    @JoinColumn(name = "thread_id")
    private Thread thread;

    public Admission(String content) {
        this.content = content;
    }
}
