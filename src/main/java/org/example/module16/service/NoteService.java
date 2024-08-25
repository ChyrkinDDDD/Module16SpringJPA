package org.example.module16.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.module16.dao.model.Note;
import org.example.module16.dao.model.dto.NoteAddRequest;
import org.example.module16.dao.model.dto.NoteResponse;
import org.example.module16.dao.model.dto.NoteUpdateRequest;
import org.example.module16.exception.NoteNotFoundByThisIdException;
import org.example.module16.mapper.NoteMapper;
import org.example.module16.repository.NoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public List<Note> listAll(){
        return noteRepository.findAll();
    }

    @Transactional
    public NoteResponse update(Long id, NoteUpdateRequest request) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        return noteMapper.toNoteResponse(note);
    }

    @Transactional
    public NoteResponse create(NoteAddRequest request) {
        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        noteRepository.save(note);
        return noteMapper.toNoteResponse(note);
    }

    public NoteResponse getById(Long id) {
        return noteMapper.toNoteResponse(
                noteRepository.findById(id).orElseThrow(NoteNotFoundByThisIdException::new)
        );
    }

    public Note add(Note note){
        noteRepository.save(note);
        return note;
    }

    public void deleteById(long id){
        if(noteRepository.existsById(id)){
            noteRepository.deleteById(id);
        }else {
            throw new NoteNotFoundByThisIdException();
        }
    }

    public void update(Note note){
        if(noteRepository.existsById(note.getId())){
            noteRepository.save(note);
        }else{
            throw new NoteNotFoundByThisIdException();
        }
    }

    public Note getById(long id){
        return noteRepository.findById(id).orElseThrow(NoteNotFoundByThisIdException::new);
    }
}
