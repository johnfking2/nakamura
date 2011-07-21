package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public abstract class LangStringType extends Serialize {
  private LangString langString;
  
  protected String langStringName = "langstring";
  
  public LangStringType() {
    super();
  }
  
  public LangStringType(JSONObject json) {
    super(json, "");
  }
  
  public LangStringType(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  protected void setElementName() {
    langStringName = prefix + "langstring";
  }

  @Override
  protected void init() {
 //   System.out.println("Lang string type init begin");
 //   System.out.println(json);
    JSONObject langStringObject = JSONUtil.getJSONObject(json, langStringName);
    if (langStringObject == null) {
      String content = JSONUtil.getStringValue(json, langStringName);
      if (content == null) {
        LangString ls = new LangString(json, prefix);
        if (ls.getLanguage() != null || ls.getString() != null) {
          langString = ls;
        }
      } else {
        langString = new LangString();
        langString.setString(JSONUtil.getStringValue(json, langStringName));
      }
    } else {
      langString = new LangString(langStringObject, prefix);
    }
//    System.out.println("Lang string type init ends");
  }
  
  public LangString getLangString() {
    return langString;
  }

  public void setLangString(LangString langString) {
    this.langString = langString;
  }
}
