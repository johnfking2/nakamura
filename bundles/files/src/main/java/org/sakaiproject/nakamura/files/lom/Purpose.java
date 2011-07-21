package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Purpose extends VocabularyType {

  private String[] vocabulary = new String[]{"discipline", "idea", "prerequisite", "educational objective",
      "accessibility restrictions", "educational level", "skill level", "security level", "competency"};
  
  public Purpose() {
    super();
  }
  
  public Purpose(JSONObject json) {
    super(json, "");
  }
  
  public Purpose(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
