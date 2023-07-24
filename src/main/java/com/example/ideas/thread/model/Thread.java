package com.example.ideas.thread.model;

import com.example.ideas.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/*@Entity
@Data
@NoArgsConstructor*/
public class Thread {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String title;
    private String description;
    private String Justification;
    private String photo;
    private int points;

    @ManyToOne
    @JoinColumn
    private User user;
*/

    //@OneToMany
    //@JoinColumn -  czy join column sie pisze tylko tam gdzie jest foreign key czy wszedzie ?
    //categoryId jako pole czy obiekt ?

}
