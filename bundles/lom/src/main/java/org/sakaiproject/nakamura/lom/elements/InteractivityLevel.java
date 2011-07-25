package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class InteractivityLevel extends VocabularyType {

  private String[] vocabulary = new String[] {"very low", "low", "medium", "high", "very high"};
  
  public InteractivityLevel() {
    super();
  }
  
  public InteractivityLevel(JSONObject json) {
    super(json);
  }

  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
