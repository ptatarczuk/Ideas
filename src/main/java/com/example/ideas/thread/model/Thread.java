package com.example.ideas.thread.model;

import com.example.ideas.category.model.Category;
import com.example.ideas.department.model.Department;
import com.example.ideas.opinionOne.model.OpinionOne;
import com.example.ideas.opinionTwo.model.OpinionTwo;
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
    private Long userId;

    private String title;
    private String description;
    private String Justification;
    private String photo;
    private int points;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

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

}
