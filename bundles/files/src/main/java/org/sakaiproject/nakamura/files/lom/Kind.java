package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Kind extends VocabularyType {

  private String[] vocabulary = new String[] {"ispartof", "haspart", "isversionof", "hasversion", "isformatof",
      "hasformat", "references", "isreferencedby", "isbasedon", "isbasisfor", "requires", "isrequiredby"};
  
  public Kind() {
    super();
  }
  
  public Kind(JSONObject json) {
    super(json, "");
  }
  
  public Kind(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
