package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class Status extends VocabularyType {

  private String[] vocabulary = new String[] {"draft", "final", "revised", "unavailable"};
  
  public Status() {
    super();
  }
  
  public Status(JSONObject json) {
    super(json);
  }

  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
