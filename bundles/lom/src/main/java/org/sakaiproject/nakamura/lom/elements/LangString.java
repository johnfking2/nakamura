package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

public class LangString extends Serialize {
  
  private String language;
  private String string;

  public LangString() {
    super();
  }
  
  public LangString(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String languageName = "language";
    String stringName = "string";
    String languageJSON = JSONUtil.getStringValue(json, languageName);
    String stringJSON = JSONUtil.getStringValue(json, stringName);
    language = languageJSON;
    string = stringJSON;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }
}
