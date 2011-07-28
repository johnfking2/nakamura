package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class IntendedEndUserRole extends VocabularyType {

  private String[] vocabulary = new String[] {"teacher", "author", "learner", "manager"};
  
  public IntendedEndUserRole() {
    super();
  }
  
  public IntendedEndUserRole(JSONObject json) {
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
    return new String("<intendedEndUserRole>" + super.generateXML() + "</intendedEndUserRole>");
  }
}
