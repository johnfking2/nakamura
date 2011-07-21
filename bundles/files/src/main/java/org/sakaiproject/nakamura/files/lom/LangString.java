package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public class LangString extends Serialize {
  
  private String language;
  private String string;

  protected String languageName = "language";
  protected String stringName = "string";
  
  public LangString() {
    super();
  }
  
  public LangString(JSONObject json) {
    super(json, "");
  }
  
  public LangString(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  protected void setElementName() {
    languageName = prefix + "language";
    stringName = prefix + "string";
  }

  @Override
  protected void init() {
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
