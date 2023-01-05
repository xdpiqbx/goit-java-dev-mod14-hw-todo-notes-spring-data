package com.dpiqb.notes;

import com.dpiqb.notes.storage.NoteMapStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

public class NoteServiceTest {
  static NoteMapStorage storage;
  static NoteService service;
  @BeforeAll
  public static void init(){
    storage = new NoteMapStorage();
    service = new NoteService(storage);
    Map<Long, Note> data = Collections.synchronizedMap(new LinkedHashMap<>());
    String[] mockTitle = new String[]{"Title 001", "Title 002", "Title 003"};
    String[] mockContent = new String[]{"Content 001", "Content 002", "Content 003"};
    int i = 0;
    for (String title : mockTitle) {
      Note note = new Note();
      note.setTitle(title);
      note.setContent(mockContent[i]);
      long id = note.hashCode();
      note.setId(id);
      data.put(id, note);
      i++;
    }
    storage.setStorage(data);
  }
  @Test
  public void listAllTest(){
    List<Note> notes = service.listAll();
    for (Note note : notes) {
      System.out.println(note);
    }
  }
  @Test
  public void addTest(){
    Note note = new Note();
    note.setTitle("New Title");
    note.setContent("New Content");
    System.out.println("Added note: " + service.add(note));
    List<Note> notes = service.listAll();
    for (Note nte : notes) {
      System.out.println(nte);
    }
  }
  @Test
  public void deleteByIdTest(){
    service.deleteById(service.listAll().get(1).getId());
    List<Note> notes = service.listAll();
    for (Note nte : notes) {
      System.out.println(nte);
    }
  }
  @Test
  public void updateTest(){
    Note note = service.listAll().get(1);
    note.setTitle("Updated TITLE");
    service.update(note);
    List<Note> notes = service.listAll();
    for (Note nte : notes) {
      System.out.println(nte);
    }
  }
  @Test
  public void getByIdTest(){
    System.out.println(service.getById(service.listAll().get(1).getId()));
  }
}
