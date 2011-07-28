package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class Structure extends VocabularyType {

  private String[] vocabulary = {"atomic", "collection", "networked", "hierarchical", "linear"};
  
  public Structure() {
    super();
  }
  
  public Structure(JSONObject json) {
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
    return new String("<structure>" + super.generateXML() + "</structure>");
  }
}
