package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class AggregationLevel extends VocabularyType {

  private String[] vocabulary = new String[] {"1", "2", "3", "4"};
  
  public AggregationLevel() {
    super();
  }
  
  public AggregationLevel(JSONObject json) {
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
    return new String ("<aggregationLevel>" + super.generateXML() + "</aggregationLevel>");
  }
}
