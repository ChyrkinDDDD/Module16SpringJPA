package org.example.module16.service;


import lombok.RequiredArgsConstructor;
import org.example.module16.dao.model.Note;
import org.example.module16.exception.NoteNotFoundByThisIdException;
import org.example.module16.repository.NoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> listAll(){
        return noteRepository.findAll();
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
