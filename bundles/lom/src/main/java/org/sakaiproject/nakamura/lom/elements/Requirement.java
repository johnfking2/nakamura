package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

import java.util.ArrayList;
import java.util.List;

public class Requirement extends Serialize {

  private List<OrComposite> orComposite; 
  
  public Requirement() {
    super();
  }
  
  public Requirement(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String orCompositeName = "orComposite";
    JSONObject orJSON = JSONUtil.getJSONObject(json, orCompositeName);
    if (orJSON == null) {
      JSONArray orArray = JSONUtil.getJSONArray(json, orCompositeName);
      if (orArray != null) {
        for (int i = 0; i < orArray.length(); i++){
          JSONObject object = orArray.optJSONObject(i);
          if (object != null) {
            addOrComposite(new OrComposite(object));
          }
        }
      }
    } else {
      addOrComposite (new OrComposite(orJSON));
    }

  }
  
  public List<OrComposite> getOrComposite() {
    return orComposite;
  }

  public void addOrComposite(OrComposite or) {
    if (orComposite == null) {
      orComposite = new ArrayList<OrComposite>();
    }
    orComposite.add(or);
  }
  
  public void setOrComposite(List<OrComposite> orComposite) {
    this.orComposite = orComposite;
  }
  
  @Override
  public String generateXML() {
    StringBuilder sb = new StringBuilder("");
    if (this.getOrComposite() != null) {
      for (int i = 0; i < this.getOrComposite().size(); i++) {
        sb.append(this.getOrComposite().get(i).generateXML());
      }
    }
    if (sb.toString().equals(""))
      return "";
    return new String("<requirement>" + sb.toString() + "</requirement>");
  }

}
