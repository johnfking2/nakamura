package org.sakaiproject.nakamura.files.cp;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.ManifestErrorException;

import java.util.ArrayList;
import java.util.List;

public class Manifest {
  private Organizations organizations;
  private Resources resources;
  private List<Manifest> subManifests;
  private String identifier;
  private String version;
  private String xmlBase;
  private Metadata metadata;
  
  private JSONObject json;
  private String prefix = "";
  private String lomPrefix = ""; 
  
  protected String organizationsName = "organizations";
  protected String resourcesName = "resources";
  protected String manifestName = "manifest";
  protected String identifierName = "identifier";
  protected String versionName = "version";
  protected String xmlBaseName = "xmlbase";
  protected String metadataName = "metadata";
  protected String lomName = "lom";
      
  public Manifest() {
    super();
  }
  
  public Manifest(JSONObject json) throws ManifestErrorException {
    this.setElementName();
    this.setJSON(json);
    this.init();
  }
  
  public Manifest(JSONObject json, String prefix, String lomPrefix) throws ManifestErrorException{
    this.prefix = prefix;
    this.lomPrefix = lomPrefix;
    this.setElementName();
    this.setJSON(json);
    this.init();
  }

  protected void init() throws ManifestErrorException{
    JSONObject orgJSON = JSONUtil.getJSONObject(json, organizationsName);
    if (orgJSON != null) {
      organizations = new Organizations(orgJSON, prefix, lomPrefix);
    } else {
      throw new ManifestErrorException("There is not Organizations element in manifest.");
    }
    
    JSONObject resourcesJSON = JSONUtil.getJSONObject(json, resourcesName);
    if (resourcesJSON != null) {
      resources = new Resources(resourcesJSON, prefix, lomPrefix);
    } else {
      throw new ManifestErrorException("There is not Resources element in manifest");
    }
    
    JSONObject metaJSON = JSONUtil.getJSONObject(json, metadataName);
    if (metaJSON !=null) {
      metadata = new Metadata(metaJSON, prefix, lomPrefix);
    } else {
      if (JSONUtil.getJSONObject(json, lomName) != null) {
        metadata = new Metadata(json, prefix, lomPrefix);
      }
    }
    
    JSONObject subManifestJSON = JSONUtil.getJSONObject(json, manifestName);
    if (subManifestJSON == null) {
      JSONArray subArray = JSONUtil.getJSONArray(json, manifestName);
      if (subArray != null) {
        for (int i = 0; i < subArray.length(); i++) {
          JSONObject object = subArray.optJSONObject(i);
          if (object != null) {
            try {
              JSONObject m = new JSONObject();
              m.put(manifestName, object);
              addSubManifest(new Manifest(m, prefix, lomPrefix));
            } catch (JSONException e) {
              
            }
          }
        }
      }
    } else {
      try {
        JSONObject m = new JSONObject();
        m.put(manifestName, subManifestJSON);
        addSubManifest(new Manifest(m, prefix, lomPrefix));
      } catch (JSONException e) {
        
      }
    }
    identifier = JSONUtil.getStringValue(json, identifierName);
    xmlBase = JSONUtil.getStringValue(json, xmlBaseName);
    version = JSONUtil.getStringValue(json, versionName);
  }
  
  protected void setElementName() {
    organizationsName = prefix + "organizations";
    resourcesName = prefix + "resources";
    manifestName = prefix + "manifest";
    identifierName = prefix + "identifier";
    versionName = prefix + "version";
    xmlBaseName = prefix + "xmlbase";
  }  
  
  protected void setJSON(JSONObject json) throws ManifestErrorException{
    JSONObject j = JSONUtil.getJSONObject(json, manifestName);
    if (j == null)
      throw new ManifestErrorException("Manifest element is not found");
    this.json = j;
  }
  public Organizations getOrganizations() {
    return organizations;
  }

  public void setOrganizations(Organizations organizations) {
    this.organizations = organizations;
  }

  public Resources getResources() {
    return resources;
  }

  public void setResources(Resources resources) {
    this.resources = resources;
  }

  public List<Manifest> getSubManifests() {
    return subManifests;
  }
  
  public void addSubManifest(Manifest m) {
    if (subManifests == null)
      subManifests = new ArrayList<Manifest>();
    subManifests.add(m);
  }

  public void setSubManifests(List<Manifest> subManifests) {
    this.subManifests = subManifests;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getXmlBase() {
    return xmlBase;
  }

  public void setXmlBase(String xmlBase) {
    this.xmlBase = xmlBase;
  }

  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }
}
