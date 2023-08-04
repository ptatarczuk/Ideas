package com.example.ideas.util_Entities.stage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stage {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stageId;

    private String stageName;
}
