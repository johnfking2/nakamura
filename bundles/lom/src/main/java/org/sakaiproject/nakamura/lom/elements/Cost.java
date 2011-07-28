package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class Cost extends VocabularyType {

  private String[] vocabulary = new String[] {"yes", "no"};
  
  public Cost() {
    super();
  }
  
  public Cost(JSONObject json) {
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
    return new String ("<cost>" + super.generateXML() + "</cost>");
  }
}
