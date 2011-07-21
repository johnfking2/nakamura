package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Cost extends VocabularyType {

  private String[] vocabulary = new String[] {"yes", "no"};
  
  public Cost() {
    super();
  }
  
  public Cost(JSONObject json) {
    super(json, "");
  }
  
  public Cost(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
