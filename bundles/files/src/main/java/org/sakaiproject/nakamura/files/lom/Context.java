package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Context extends VocabularyType {

  private String[] vocabulary = new String[] {"school", "higher education", "training", "other"};
  
  public Context() {
    super();
  }
  
  public Context(JSONObject json) {
    super(json, "");
  }
  
  public Context (JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
