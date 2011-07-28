package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class InteractivityType extends VocabularyType {

  private String[] vocabulary = new String[] {"active", "expositive", "mixed"};
  
  public InteractivityType() {
    super();
  }
  
  public InteractivityType(JSONObject json) {
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
    return new String("<interactivityType>" + super.generateXML() + "</interactivityType>");
  }

}
