package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class SemanticDensity extends VocabularyType {

  private String[] vocabulary = new String[] {"very low", "low", "medium", "high", "very high"};
  
  public SemanticDensity() {
    super();
  }
  
  public SemanticDensity(JSONObject json) {
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
    return new String("<semanticDensity>" + super.generateXML() + "</semanticDensity>");
  }
}
