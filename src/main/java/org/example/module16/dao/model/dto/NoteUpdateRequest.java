package org.example.module16.dao.model.dto;

import lombok.Data;

@Data
public class NoteUpdateRequest {
    private int id;
    private String title;
    private String content;
}
