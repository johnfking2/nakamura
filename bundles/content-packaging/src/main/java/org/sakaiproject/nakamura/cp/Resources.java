package org.sakaiproject.nakamura.cp;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

import java.util.ArrayList;
import java.util.List;

public class Resources extends Serialize {
  
  private List<Resource> resources;
  private String xmlBase;

  public Resources() {
    super();
  }
  
  public Resources(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String resourceName = "resource";
    String xmlBaseName = "base";
    JSONObject resourceJSON = JSONUtil.getJSONObject(json, resourceName);
    if (resourceJSON == null) {
      JSONArray resourceArray = JSONUtil.getJSONArray(json, resourceName);
      if (resourceArray != null) {
        for (int i = 0; i < resourceArray.length(); i++) {
          JSONObject object = resourceArray.optJSONObject(i);
          if (object != null) 
            addResource(new Resource(object));
        }
      }
    } else {
      addResource(new Resource(resourceJSON));
    }
    
    xmlBase = JSONUtil.getStringValue(json, xmlBaseName);
  }

  public List<Resource> getResources() {
    return resources;
  }

  public void addResource(Resource r) {
    if (resources == null)
      resources = new ArrayList<Resource>();
    resources.add(r);
  }
  
  public void addResource(Resource r, int index) {
    if (resources == null)
      resources = new ArrayList<Resource>();
    resources.add(index, r);
  }
  
  public Resource searchResource (String id) {
    if (resources == null || id == null || "".equals(id)) {
      return null;
    }
    for (Resource r : resources)
      if (id.equalsIgnoreCase(r.getIdentifier()))
        return r;
    return null;
  }
  
  public void setResources(List<Resource> resources) {
    this.resources = resources;
  }

  public String getXmlBase() {
    return xmlBase;
  }

  public void setXmlBase(String xmlBase) {
    this.xmlBase = xmlBase;
  }
  
  @Override
  public String generateXML() {
    StringBuilder head = new StringBuilder("<resources");
    StringBuilder sb = new StringBuilder("");
    if (this.getXmlBase() != null) {
      head.append(" xml:base=\"" + this.getXmlBase() + "\"");
    }
    if (this.getResources() != null) {
      for (int i = 0; i < this.getResources().size(); i++){
        sb.append(this.getResources().get(i).generateXML());
      }
    }

    return new String(head.toString() + ">" + sb.toString() + "</resources>");
  }
}
