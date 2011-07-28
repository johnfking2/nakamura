package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class Kind extends VocabularyType {

  private String[] vocabulary = new String[] {"ispartof", "haspart", "isversionof", "hasversion", "isformatof",
      "hasformat", "references", "isreferencedby", "isbasedon", "isbasisfor", "requires", "isrequiredby"};
  
  public Kind() {
    super();
  }
  
  public Kind(JSONObject json) {
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
    return new String("<kind>" + super.generateXML() + "</kind>");
  }
}
