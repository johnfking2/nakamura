package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class IntendedEndUserRole extends VocabularyType {

  private String[] vocabulary = new String[] {"teacher", "author", "learner", "manager"};
  
  public IntendedEndUserRole() {
    super();
  }
  
  public IntendedEndUserRole(JSONObject json) {
    super(json, "");
  }
  
  public IntendedEndUserRole(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
