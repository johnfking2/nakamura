package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Structure extends VocabularyType {

  private String[] vocabulary = {"atomic", "collection", "networked", "hierarchical", "linear"};
  
  public Structure() {
    super();
  }
  
  public Structure(JSONObject json) {
    super(json, "");
  }
  
  public Structure(JSONObject json, String prefix) {
    super(json, prefix);
  }

  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }
}
