package com.dpiqb.notes.mvc;

import com.dpiqb.notes.Note;
import com.dpiqb.notes.NoteService;
import com.dpiqb.notes.storage.NoteMapStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/note")
@Controller
public class NoteController {
  private final NoteService noteService;
  @GetMapping("/list")
  public ModelAndView getListOfNotes(){
    ModelAndView result = new ModelAndView("notes");
    result.addObject("noteList", noteService.listAll());
    return result;
  }
  @PostMapping("/delete")
  public RedirectView deleteNote(@RequestParam long id){
    noteService.deleteById(id);
    return new RedirectView("/note/list");
  }
  @GetMapping("/edit")
  public ModelAndView editNote(@RequestParam Long id){
    ModelAndView result = new ModelAndView("addOrEditNote");
    result.addObject("note", noteService.getById(id));
    return result;
  }
  @PostMapping("/edit")
  public RedirectView editNote(@RequestParam long id, @RequestParam String title, @RequestParam String content){
    Note note = new Note();
    note.setId(id);
    note.setTitle(title);
    note.setContent(content);
    noteService.update(note);
    return new RedirectView("/note/list");
  }
  @GetMapping("/add")
  public ModelAndView addNote(){
    ModelAndView result = new ModelAndView("addOrEditNote");
    result.addObject("note", null);
    return result;
  }
  @PostMapping("/add")
  public RedirectView addNote(@RequestParam String title, @RequestParam String content){
    Note note = new Note();
    note.setTitle(title);
    note.setContent(content);
    noteService.add(note);
    return new RedirectView("/note/list");
  }

  @GetMapping("/addmock")
  public RedirectView addMock(){
    Map<String, String> mockData = NoteMapStorage.getMockData();

    mockData.forEach((key, value) -> {
      Note note = new Note();
      note.setTitle(key);
      note.setContent(value);
      noteService.add(note);
    });

    return new RedirectView("/note/list");
  }
}