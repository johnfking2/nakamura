package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Name extends VocabularyType {

  private String[] vocabulary = new String[] {"pc-docs", "ms-windows", "macos", "unix", "multi-os", "none",
      "any", "netscape communicator", "ms-internet explorer", "opera", "amaya"};
  
  public Name() {
    super();
  }
  
  public Name(JSONObject json) {
    super(json, "");
  }
  
  public Name(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
