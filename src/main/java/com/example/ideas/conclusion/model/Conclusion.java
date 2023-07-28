package com.example.ideas.conclusion.model;

import com.example.ideas.thread.model.Thread;
import com.example.ideas.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="conclusions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conclusion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conclusionId;

    @Column(name = "conclusion_text")
    @NotBlank(message = "Content of conclusion information is mandatory")
    private String content;

    @Column(name = "conclusion_points")
    private Integer points;

    @Column(name = "conclusion_date")
    private final LocalDate dateOfPost = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "conclusion_author")
    private User user;

    @OneToOne
    @JoinColumn(name = "thread_id")
    private Thread thread;

    public Conclusion(String content, Integer points) {

        this.content = content;
        this.points = points;
    }
}
