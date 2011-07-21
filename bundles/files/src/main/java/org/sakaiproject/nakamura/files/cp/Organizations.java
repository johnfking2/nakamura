package org.sakaiproject.nakamura.files.cp;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

import java.util.ArrayList;
import java.util.List;

public class Organizations extends Serialize {
  private String defaultID;
  private List<Organization> organizations;
  private String lomPrefix = "";
  
  protected String defaultName = "default";
  protected String organizationName = "organization";
  
  public Organizations() {
    super();
  }
  
  public Organizations(JSONObject json) {
    super(json, "");
  }
  
  public Organizations(JSONObject json, String prefix, String lomPrefix) {
    this.json = json;
    this.prefix = prefix;
    this.lomPrefix = lomPrefix;
    this.setElementName();
    this.init();
  }

  @Override
  protected void init() {
    defaultID = JSONUtil.getStringValue(json, defaultName);
    
    JSONObject orgJSON = JSONUtil.getJSONObject(json, organizationName);
    if (orgJSON == null) {
      JSONArray orgArray = JSONUtil.getJSONArray(json, organizationName);
      if (orgArray != null) {
        for (int i = 0; i < orgArray.length(); i++) {
          JSONObject object = orgArray.optJSONObject(i);
          if (object != null)
            addOrganization(new Organization(object, prefix, lomPrefix));
        }
      }
    } else {
      addOrganization(new Organization(orgJSON, prefix, lomPrefix));
    }
    
  }

  @Override
  protected void setElementName() {
    defaultName = prefix + "default";
    organizationName = prefix + "organization";
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
}
