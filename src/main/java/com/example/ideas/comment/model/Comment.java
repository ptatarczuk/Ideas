package com.example.ideas.comment.model;


import com.example.ideas.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;


    @NotBlank
    @Column(name="comment_text")
    private String commentText;

    @Column(name="comment_date")
    private LocalDate commentDate;

    @OneToOne
    @PrimaryKeyJoinColumn
    //sprawdzić, czy to jest na pewno poprawne
    private User user;
 //   private Thread thread;


}
