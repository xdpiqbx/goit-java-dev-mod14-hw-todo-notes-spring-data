package com.dpiqb.notes.storage;

import com.dpiqb.notes.Note;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Service
public class NoteMapStorage{
  private Map<Long, Note> storage = Collections.synchronizedMap(new LinkedHashMap<>(fillMockData()));
  private static Map<Long, Note> fillMockData(){
    Map<Long, Note> filledByMockData = new LinkedHashMap<>();
    Map<String, String> mockData = getMockData();
    for (String key: mockData.keySet()) {
      Note note = new Note();
      String value = mockData.get(key);
      long id = (key+value).hashCode();
      note.setId(id);
      note.setTitle(key);
      note.setContent(value);
      filledByMockData.put(id, note);
    }
    return filledByMockData;
  }
  public static Map<String, String> getMockData(){
    Map<String, String> data = new LinkedHashMap<>();
    data.put(
      "Single Responsibility Principle",
      "a class should do one thing and therefore it should have only a single reason to change"
    );
    data.put(
      "Open-Closed Principle",
      "classes should be open for extension and closed to modification"
    );
    data.put(
      "Liskov Substitution Principle",
      "subclasses should be substitutable for their base classes"
    );
    data.put(
      "Interface Segregation Principle",
      "is about separating the interfaces"
    );
    data.put(
      "Dependency Inversion Principle",
      "classes should depend upon interfaces or abstract classes instead of concrete classes and functions"
    );
    return data;
  }
}
