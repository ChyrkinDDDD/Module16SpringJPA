package org.example.module16.dao.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITLE",length = 50)
    private String title;
    @Column(name = "CONTENT",length = 1000)
    private String content;
}
