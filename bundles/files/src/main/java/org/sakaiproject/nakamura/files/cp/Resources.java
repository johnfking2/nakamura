package org.sakaiproject.nakamura.files.cp;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

import java.util.ArrayList;
import java.util.List;

public class Resources extends Serialize {
  
  private List<Resource> resources;
  private String xmlBase;
  protected String lomPrefix = "";
  
  protected String resourceName = "resource";
  protected String xmlBaseName = "xmlbase";
  
  public Resources() {
    super();
  }
  
  public Resources(JSONObject json) {
    this.json = json;
    this.prefix = "";
    this.lomPrefix = "";
    this.setElementName();
    this.init();
  }
  
  public Resources(JSONObject json, String prefix, String lomPrefix){
    this.json = json;
    this.prefix = prefix;
    this.lomPrefix = lomPrefix;
    this.setElementName();
    this.init();
  }


  @Override
  protected void init() {
    JSONObject resourceJSON = JSONUtil.getJSONObject(json, resourceName);
    if (resourceJSON == null) {
      JSONArray resourceArray = JSONUtil.getJSONArray(json, resourceName);
      if (resourceArray != null) {
        for (int i = 0; i < resourceArray.length(); i++) {
          JSONObject object = resourceArray.optJSONObject(i);
          if (object != null) 
            addResource(new Resource(object, prefix, lomPrefix));
        }
      }
    } else {
      addResource(new Resource(resourceJSON, prefix, lomPrefix));
    }
    
    xmlBase = JSONUtil.getStringValue(json, xmlBaseName);
  }
  
  @Override
  protected void setElementName() {
    resourceName = prefix + "resource";
    xmlBaseName = prefix + "xmlbase";
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
}
