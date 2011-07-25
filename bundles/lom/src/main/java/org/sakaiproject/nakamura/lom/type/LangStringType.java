package org.sakaiproject.nakamura.lom.type;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.elements.LangString;

public abstract class LangStringType extends Serialize {
  private LangString langString;
 
  public LangStringType() {
    super();
  }
  
  public LangStringType(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String langStringName = "langstring";
    JSONObject langStringObject = JSONUtil.getJSONObject(json, langStringName);
    if (langStringObject == null) {
      String content = JSONUtil.getStringValue(json, langStringName);
      if (content == null) {
        LangString ls = new LangString(json);
        if (ls.getLanguage() != null || ls.getString() != null) {
          langString = ls;
        }
      } else {
        langString = new LangString();
        langString.setString(JSONUtil.getStringValue(json, langStringName));
      }
    } else {
      langString = new LangString(langStringObject);
    }
  }
  
  public LangString getLangString() {
    return langString;
  }

  public void setLangString(LangString langString) {
    this.langString = langString;
  }
}
