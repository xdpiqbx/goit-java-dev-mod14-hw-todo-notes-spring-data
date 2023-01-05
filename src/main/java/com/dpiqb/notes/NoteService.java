package com.dpiqb.notes;

import com.dpiqb.notes.storage.NoteMapStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteService implements NoteCRUD{

  private final NoteMapStorage storage;

  @Override
  public List<Note> listAll() {
    return storage.getStorage().values().stream().toList();
  }

  @Override
  public Note add(Note note) {
    note.setId((note.getTitle() + note.getContent()).hashCode());
    storage.getStorage().put(note.getId(), note);
    return note;
  }

  @Override
  public void deleteById(long id) {
    storage.getStorage().remove(id);
  }

  @Override
  public void update(Note note) {
    storage.getStorage().put(note.getId(), note);
  }

  @Override
  public Note getById(long id) {
    return storage.getStorage().get(id);
  }
}
