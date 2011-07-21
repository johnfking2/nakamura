package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class InteractivityLevel extends VocabularyType {

  private String[] vocabulary = new String[] {"very low", "low", "medium", "high", "very high"};
  
  public InteractivityLevel() {
    super();
  }
  
  public InteractivityLevel(JSONObject json) {
    super(json, "");
  }
  
  public InteractivityLevel(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
