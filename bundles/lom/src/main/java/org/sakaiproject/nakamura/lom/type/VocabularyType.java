package org.sakaiproject.nakamura.lom.type;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.elements.LangString;
import org.sakaiproject.nakamura.lom.elements.Source;
import org.sakaiproject.nakamura.lom.elements.Value;

public abstract class VocabularyType extends Serialize {

  private Source source;
  private Value value;

  public VocabularyType() {
    super();
  }
  
  public VocabularyType(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String sourceName = "source";
    String valueName = "value";
    JSONObject sourceJSON = JSONUtil.getJSONObject(json, sourceName);
    if (sourceJSON == null) {
      String s = JSONUtil.getStringValue(json, sourceName);
      this.setSource(s);
    } else {
      source = new Source(sourceJSON);
    }

    JSONObject valueJSON = JSONUtil.getJSONObject(json, valueName);
    if (valueJSON == null) {
      String v = JSONUtil.getStringValue(json, valueName);
      this.setValue(v);
    } else {
      value = new Value(valueJSON);
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
  @Override
  public String generateXML() {
    StringBuilder sb = new StringBuilder("");
    if (this.getSource() != null)
      sb.append("<source>" + this.getSource() + "</source>");
    if (this.getValue() != null)
      sb.append("<value>" + this.getValue() + "</value>");
    return sb.toString();
  }
}
