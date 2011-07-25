package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class Context extends VocabularyType {

  private String[] vocabulary = new String[] {"school", "higher education", "training", "other"};
  
  public Context() {
    super();
  }
  
  public Context(JSONObject json) {
    super(json);
  }

  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
