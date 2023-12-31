package com.example.ideas.conclusion.model;

import com.example.ideas.thread.model.Thread;
import com.example.ideas.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="conclusions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conclusion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conclusionId;

    @Column(name = "conclusion_text")
    @NotBlank(message = "Content of conclusion information is mandatory")
    private String content;

    @Column(name = "conclusion_date")
    private LocalDate dateOfPost;

    @OneToOne
    @JoinColumn(name = "conclusion_author")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "conclusion_id")
    @JsonIgnore
    private Thread thread;

    public Conclusion(String content, Integer points) {
        this.content = content;
    }
}
