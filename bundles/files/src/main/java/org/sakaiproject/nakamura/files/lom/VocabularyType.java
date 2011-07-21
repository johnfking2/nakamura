package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public abstract class VocabularyType extends Serialize {

  private Source source;
  private Value value;
  
  protected String sourceName = "source";
  protected String valueName = "value";
  
  public VocabularyType() {
    super();
  }
  
  public VocabularyType(JSONObject json) {
    super(json, "");
  }
  
  public VocabularyType(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  protected void setElementName() {
    sourceName = prefix + "source";
    valueName = prefix + "value";
  }

  @Override
  protected void init() {
   // System.out.println("vocabulary init begin:");
    JSONObject sourceJSON = JSONUtil.getJSONObject(json, sourceName);
   // System.out.println(sourceJSON);
    if (sourceJSON == null) {
      String s = JSONUtil.getStringValue(json, sourceName);
      this.setSource(s);
    } else {
      source = new Source(sourceJSON, prefix);
    }

    JSONObject valueJSON = JSONUtil.getJSONObject(json, valueName);
    if (valueJSON == null) {
      String v = JSONUtil.getStringValue(json, valueName);
      this.setValue(v);
    } else {
      value = new Value(valueJSON, prefix);
    }
  }
  
  public String getSource() {
    if (source != null)
      return source.getLangString().getString();
    else 
      return null;
  }

  public void setSource(String source) {
    Source s = new Source();
    LangString l = new LangString();
    l.setString(source);
    s.setLangString(l);
    this.source = s;
  }

  public String getValue() {
    if (value == null)
      return null;
    return value.getLangString().getString();
  }

  public void setValue(String value) {
    LangString l = new LangString();
    l.setString(value);
    Value v = new Value();
    v.setLangString(l);
    this.value = v;
  }
  
  public abstract String[] getLOMVocabulary ();
}
