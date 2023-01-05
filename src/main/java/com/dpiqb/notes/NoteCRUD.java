package com.dpiqb.notes;

import java.util.List;

public interface NoteCRUD {
  List<Note> listAll();
  Note add(Note note);
  void deleteById(long id);
  void update(Note note);
  Note getById(long id);
}
