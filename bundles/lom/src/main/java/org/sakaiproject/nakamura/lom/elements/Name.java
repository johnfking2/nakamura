package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class Name extends VocabularyType {

  private String[] vocabulary = new String[] {"pc-docs", "ms-windows", "macos", "unix", "multi-os", "none",
      "any", "netscape communicator", "ms-internet explorer", "opera", "amaya"};
  
  public Name() {
    super();
  }
  
  public Name(JSONObject json) {
    super(json);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
