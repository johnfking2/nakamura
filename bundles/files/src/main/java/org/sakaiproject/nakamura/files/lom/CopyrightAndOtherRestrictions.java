package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class CopyrightAndOtherRestrictions extends VocabularyType {

  private String[] vocabulary = new String[] {"yes", "no"};
  
  public CopyrightAndOtherRestrictions() {
    super();
  }
  
  public CopyrightAndOtherRestrictions(JSONObject json) {
    super(json, "");
  }
  
  public CopyrightAndOtherRestrictions(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
