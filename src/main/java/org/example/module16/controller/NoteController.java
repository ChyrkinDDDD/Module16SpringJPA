package org.example.module16.controller;

import lombok.RequiredArgsConstructor;
import org.example.module16.dao.model.Note;
import org.example.module16.dao.model.dto.NoteAddRequest;
import org.example.module16.dao.model.dto.NoteResponse;
import org.example.module16.dao.model.dto.NoteUpdateRequest;
import org.example.module16.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoteController {
    @Autowired
    private final NoteService noteService;

    @GetMapping("/api/note")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.listAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @DeleteMapping("/api/note/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteById(id);
    }

    @PutMapping("/api/note/{id}")
    public NoteResponse updateNote(@PathVariable Long id, @RequestBody NoteUpdateRequest request) {
        return noteService.update(id, request);
    }

    @GetMapping("/api/note/{id}")
    public NoteResponse findById(@PathVariable Long id) {
        return noteService.getById(id);
    }

    @PostMapping("/api/note")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse createNew(@RequestBody NoteAddRequest request) {
        System.out.println("request = " + request);
        return noteService.create(request);
    }

    @GetMapping(value = "/create")
    public ModelAndView create() {
        return new ModelAndView("/note/noteCreate");
    }

    @PostMapping(value = "/create")
    public RedirectView create(@ModelAttribute Note note) {
        noteService.add(note);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        return redirectView;
    }

    @GetMapping("/note/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("note/noteList");
        modelAndView.addObject("notes", noteService.listAll());
        return modelAndView;
    }

    @PostMapping("/note/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/note/edit")
    public String editPage(Model model, @RequestParam(name = "id") long id) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "note/noteEdit";
    }

    @PostMapping("/note/edit")
    public RedirectView edit(@ModelAttribute("note") Note note) {
        RedirectView redirectView = new RedirectView("/note/list");
        noteService.update(note);
        return redirectView;
    }
}
