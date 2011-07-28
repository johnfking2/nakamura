package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class Difficulty extends VocabularyType {

  private String[] vocabulary = new String[] {"very easy", "easy", "medium", "difficult", "very difficult"};
  
  public Difficulty() {
    super();
  }
  
  public Difficulty(JSONObject json) {
    super(json);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

  @Override
  public String generateXML() {
    if (super.generateXML().equals(""))
      return "";
    return new String("<difficulty>" + super.generateXML() + "</difficulty>");
  }
}
