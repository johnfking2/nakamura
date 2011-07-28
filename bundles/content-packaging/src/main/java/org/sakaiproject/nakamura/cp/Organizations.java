package org.sakaiproject.nakamura.cp;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

import java.util.ArrayList;
import java.util.List;

public class Organizations extends Serialize {
  private String defaultID;
  private List<Organization> organizations;

  public Organizations() {
    super();
  }
  
  public Organizations(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String defaultName = "default";
    String organizationName = "organization";
    defaultID = JSONUtil.getStringValue(json, defaultName);
    
    JSONObject orgJSON = JSONUtil.getJSONObject(json, organizationName);
    if (orgJSON == null) {
      JSONArray orgArray = JSONUtil.getJSONArray(json, organizationName);
      if (orgArray != null) {
        for (int i = 0; i < orgArray.length(); i++) {
          JSONObject object = orgArray.optJSONObject(i);
          if (object != null)
            addOrganization(new Organization(object));
        }
      }
    } else {
      addOrganization(new Organization(orgJSON));
    }
    
  }

  public String getDefaultID() {
    return defaultID;
  }

  public void setDefaultID(String defaultID) {
    this.defaultID = defaultID;
  }

  public List<Organization> getOrganizations() {
    return organizations;
  }
  
  public void addOrganization(Organization o) {
    if (organizations == null) {
      organizations = new ArrayList<Organization>();
    }
    organizations.add(o);
  }
  
  public void addOrganization(Organization o, int index) {
    if (organizations == null) {
      organizations = new ArrayList<Organization>();
    }
    organizations.add(index, o);
  }
  
  public Organization getOrganization(String id) {
    if (organizations == null || id == null || "".equals(id))
      return null;
    for (Organization o : organizations) {
      if (id.equals(o.getIdentifier())) 
        return o;
    }
    return null;
  }
  
  public Item searchItem(String id) {
    if (organizations == null || id == null || "".equals(id))
      return null;
    for (Organization o : organizations) {
      Item i = o.searchSubItem(id);
      if (i != null) 
        return i;
    }
    return null;
  }

  public void setOrganizations(List<Organization> organizations) {
    this.organizations = organizations;
  }
  
  @Override
  public String generateXML() {
    StringBuilder head = new StringBuilder("<organizations");
    StringBuilder sb = new StringBuilder("");
    if (this.getDefaultID() != null) {
      head.append(" default=\"" + this.getDefaultID() + "\"");
    }
    if (this.getOrganizations() != null) {
      for (int i = 0; i < this.getOrganizations().size(); i++){
        sb.append(this.getOrganizations().get(i).generateXML());
      }
    }
    
    return new String(head.toString() + ">" + sb.toString() + "</organizations>");
  }
}
