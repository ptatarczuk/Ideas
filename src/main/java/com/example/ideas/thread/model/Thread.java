package com.example.ideas.thread.model;

import com.example.ideas.admission.model.Admission;
import com.example.ideas.category.model.Category;
import com.example.ideas.conclusion.model.Conclusion;
import com.example.ideas.stage.model.Stage;
import com.example.ideas.status.model.Status;
import com.example.ideas.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "threads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long threadId;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column
    @NotBlank
    @Lob //powoduje zapisu w SQL jako TEXT, umożliwiając wprowadzanie długich opisów
    private String description;

    @Column(columnDefinition = "TEXT") //robi to samo co @Lob, użyte jako alternatywa
    @NotBlank
    private String justification;

    @Column(name = "photo")
    private String photo;

    // czy to dobra nazwa? Mamy dwa razy "points" (w modelu Conclusion)
    @Column(name = "points")
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

    @OneToOne
    @JoinColumn(name = "admission_id")
    private Admission admission;

    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToOne
    @JoinColumn(name = "conclusion_id")
    private Conclusion conclusion;


    //@OneToMany
    //@JoinColumn -  czy join column sie pisze tylko tam gdzie jest foreign key czy wszedzie ?
    //categoryId jako pole czy obiekt ?


    public Thread(String title, Integer points, User user) {
        this.title = title;
        this.points = points;
        this.user = user;
    }

    public Thread(String title, String description, String justification, String photo, Integer points,
                  User user, Category category, Stage stage, Admission admission, Status status,
                  Conclusion conclusion) {
        this.title = title;
        this.description = description;
        this.justification = justification;
        this.photo = photo;
        this.points = points;
        this.user = user;
        this.category = category;
        this.stage = stage;
        this.admission = admission;
        this.status = status;
        this.conclusion = conclusion;
    }
}
