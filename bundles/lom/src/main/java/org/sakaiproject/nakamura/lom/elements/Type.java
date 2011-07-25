package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class Type extends VocabularyType {

  private String[] vocabulary = new String[] {"operating system", "browser"};
  
  public Type () {
    super();
  }
  
  public Type (JSONObject json) {
    super(json);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
