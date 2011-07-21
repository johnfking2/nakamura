package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Difficulty extends VocabularyType {

  private String[] vocabulary = new String[] {"very easy", "easy", "medium", "difficult", "very difficult"};
  
  public Difficulty() {
    super();
  }
  
  public Difficulty(JSONObject json) {
    super(json, "");
  }
  
  public Difficulty(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
