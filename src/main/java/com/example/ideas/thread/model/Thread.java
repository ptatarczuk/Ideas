package com.example.ideas.thread.model;

import com.example.ideas.admission.model.Admission;
import com.example.ideas.util_Entities.category.model.Category;
import com.example.ideas.comment.model.Comment;
import com.example.ideas.conclusion.model.Conclusion;
import com.example.ideas.util_Entities.stage.model.Stage;
import com.example.ideas.util_Entities.status.model.Status;
import com.example.ideas.user.model.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "threads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long threadId;

    @Column(name = "thread_title")
    @NotBlank
    private String title;

    @Column(name = "thread_description")
    @NotBlank
    //@Lob //powoduje zapisu w SQL jako TEXT, umożliwiając wprowadzanie długich opisów
    private String description;

    @Column(columnDefinition = "TEXT", name = "thread_justification") //robi to samo co @Lob, użyte jako alternatywa
    @NotBlank
    private String justification;

    @Column(name = "thread_photo")
    private String photo;

    // czy to dobra nazwa? Mamy dwa razy "points" (w modelu Conclusion)
    @Column(name = "thread_points")
    private Integer points;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "thread", fetch = FetchType.LAZY)
    private Admission admission;

    @OneToOne(mappedBy = "thread", fetch = FetchType.LAZY)
    private Conclusion conclusion;




    //@OneToMany
    //@JoinColumn -  czy join column sie pisze tylko tam gdzie jest foreign key czy wszedzie ?
    //categoryId jako pole czy obiekt ?

}
