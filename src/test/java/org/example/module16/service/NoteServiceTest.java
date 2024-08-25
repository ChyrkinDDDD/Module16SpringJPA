package org.example.module16.service;

import org.example.module16.dao.model.Note;
import org.example.module16.dao.model.dto.NoteResponse;
import org.example.module16.dao.model.dto.NoteUpdateRequest;
import org.example.module16.mapper.NoteMapperImpl;
import org.example.module16.repository.NoteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {
    @Mock
    private NoteRepository noteRepository;
    @Spy
    private NoteMapperImpl noteMapper;
    @InjectMocks
    private NoteService noteService;

    @Test
    void testNoteIsUpdatedSuccessfully() {
        NoteUpdateRequest request = new NoteUpdateRequest();
        request.setTitle("title");
        request.setContent("content");

        Note note = new Note();
        note.setTitle("title");
        note.setContent("content");

        when(noteRepository.findById(1L))
                .thenReturn(Optional.of(note));

        NoteResponse result = noteService.update(1L, request);

        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(note);

        verify(noteRepository).findById(1L);
        verify(noteMapper).toNoteResponse(note);
    }
}