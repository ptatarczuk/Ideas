package com.example.ideas.thread.model;

import com.example.ideas.admission.model.Admission;
import com.example.ideas.category.model.Category;
import com.example.ideas.stage.model.Stage;
import com.example.ideas.user.model.User;
import jakarta.persistence.*;
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

    private String title;

    private String description;

    private String Justification;

    private String photo;

    private int points;

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





//    @OneToOne
//    @JoinColumn(name = "TODO") //TODO
//    private Status status;

//    @OneToOne
//    @JoinColumn(name = "TODO") //TODO
//    private Stage stage;

//    @OneToOne
//    @JoinColumn(name = "TODO") //TODO
//    private OpinionOne opinionOne;
//
//    @OneToOne
//    @JoinColumn(name = "TODO") //TODO
//    private OpinionTwo opinionTwo;


    //@OneToMany
    //@JoinColumn -  czy join column sie pisze tylko tam gdzie jest foreign key czy wszedzie ?
    //categoryId jako pole czy obiekt ?


    public Thread(String title, int points, User user) {
        this.title = title;
        this.points = points;
        this.user = user;
    }
}
