package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Status extends VocabularyType {

  private String[] vocabulary = new String[] {"draft", "final", "revised", "unavailable"};
  
  public Status() {
    super();
  }
  
  public Status(JSONObject json) {
    super(json);
  }
  
  public Status(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
