package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class InteractivityType extends VocabularyType {

  private String[] vocabulary = new String[] {"active", "expositive", "mixed"};
  
  public InteractivityType() {
    super();
  }
  
  public InteractivityType(JSONObject json) {
    super(json, "");
  }
  
  public InteractivityType(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    // TODO Auto-generated method stub
    return vocabulary;
  }

}
