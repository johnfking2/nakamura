package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

import java.util.ArrayList;
import java.util.List;

public class LifeCycle extends Serialize {
  private Version version;
  private Status status;
  private List<Contribute> contribute;
  
  protected String versionName = "version";
  protected String statusName = "status";
  protected String contributeName = "contribute";
  
  public LifeCycle() {
    super();
  }
  
  public LifeCycle(JSONObject json) {
    super(json, "");
  }
  
  public LifeCycle(JSONObject json, String prefix) {
    super(json, prefix);
  }
  @Override
  protected void init() {
    JSONObject versionJSON = JSONUtil.getJSONObject(json, versionName);
    if (versionJSON != null) 
      version = new Version(versionJSON, prefix);
    
    JSONObject statusJSON = JSONUtil.getJSONObject(json, statusName);
    if (statusJSON != null)
      status = new Status(statusJSON, prefix);
    
    JSONObject contributeJSON = JSONUtil.getJSONObject(json, contributeName);
    if (contributeJSON == null) {
      JSONArray contributeArray = JSONUtil.getJSONArray(json, contributeName);
      if (contributeArray != null) {
        for (int i = 0; i < contributeArray.length(); i++) {
          JSONObject object = contributeArray.optJSONObject(i);
          if (object != null)
            addContribute(new Contribute(object, prefix, Contribute.CONTRIBUTETYPE.LIFECYCLE));
        }
      }
    } else {
      addContribute(new Contribute(contributeJSON, prefix, Contribute.CONTRIBUTETYPE.LIFECYCLE));
    }
      

  }

  @Override
  protected void setElementName() {
    versionName = prefix + "version";
    statusName = prefix + "status";
    contributeName = prefix + "contribute";
    
  }
  
  public Version getVersion() {
    return version;
  }
  
  public void setVersion(Version version) {
    this.version = version;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public List<Contribute> getContribute() {
    return contribute;
  }

  public void addContribute (Contribute c) {
    if (contribute == null) {
      contribute = new ArrayList<Contribute>();
    }
    contribute.add(c);
  }
  
  public void setContribute(List<Contribute> contribute) {
    this.contribute = contribute;
  }
}
