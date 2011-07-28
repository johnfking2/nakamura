package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class Purpose extends VocabularyType {

  private String[] vocabulary = new String[]{"discipline", "idea", "prerequisite", "educational objective",
      "accessibility restrictions", "educational level", "skill level", "security level", "competency"};
  
  public Purpose() {
    super();
  }
  
  public Purpose(JSONObject json) {
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
    return new String("<purpose>" + super.generateXML() + "</purpose>");
  }
}
