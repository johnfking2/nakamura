package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class AggregationLevel extends VocabularyType {

  private String[] vocabulary = new String[] {"1", "2", "3", "4"};
  
  public AggregationLevel() {
    super();
  }
  
  public AggregationLevel(JSONObject json) {
    super(json, "");
  }
  
  public AggregationLevel(JSONObject json, String prefix) {
    super(json, prefix);
  }
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
