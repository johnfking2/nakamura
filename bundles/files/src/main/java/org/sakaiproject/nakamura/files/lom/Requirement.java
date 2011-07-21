package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

import java.util.ArrayList;
import java.util.List;

public class Requirement extends Serialize {

  private List<OrComposite> orComposite;

  protected String orCompositeName = "orComposite";
  
  public Requirement() {
    super();
  }
  
  public Requirement(JSONObject json) {
    super(json, "");
  }
  
  public Requirement(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  protected void setElementName() {
    orCompositeName = prefix + "orComposite";
  }

  @Override
  protected void init() {
    JSONObject orJSON = JSONUtil.getJSONObject(json, orCompositeName);
    if (orJSON == null) {
      JSONArray orArray = JSONUtil.getJSONArray(json, orCompositeName);
      if (orArray != null) {
        for (int i = 0; i < orArray.length(); i++){
          JSONObject object = orArray.optJSONObject(i);
          if (object != null) {
            addOrComposite(new OrComposite(object, prefix));
          }
        }
      }
    } else {
      addOrComposite (new OrComposite(orJSON, prefix));
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

}
