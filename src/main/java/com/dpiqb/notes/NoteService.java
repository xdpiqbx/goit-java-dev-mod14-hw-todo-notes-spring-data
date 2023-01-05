package com.dpiqb.notes;

import com.dpiqb.notes.storage.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteService {
  private final NoteRepository noteRepository;
  public List<Note> listAll(){
    return noteRepository.findAll();
  };
  public void add(Note note){
    noteRepository.save(note);
  }
  public void deleteById(long id){
    noteRepository.deleteById(id);
  }
  public void update(Note note){
    noteRepository.save(note);
  }
  public Note getById(long id){
    return noteRepository.getReferenceById(id);
  }
}
