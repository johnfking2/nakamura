package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class SemanticDensity extends VocabularyType {

  private String[] vocabulary = new String[] {"very low", "low", "medium", "high", "very high"};
  
  public SemanticDensity() {
    super();
  }
  
  public SemanticDensity(JSONObject json) {
    super(json, "");
  }
  
  public SemanticDensity(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
