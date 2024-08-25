package org.example.module16.mapper;

import org.example.module16.dao.model.Note;
import org.example.module16.dao.model.dto.NoteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NoteMapper {
    NoteResponse toNoteResponse(Note note);
}
