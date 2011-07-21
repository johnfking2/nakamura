package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Type extends VocabularyType {

  private String[] vocabulary = new String[] {"operating system", "browser"};
  
  public Type () {
    super();
  }
  
  public Type (JSONObject json) {
    super(json, "");
  }
  
  public  Type (JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
