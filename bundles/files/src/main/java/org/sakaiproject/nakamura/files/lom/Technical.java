package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

import java.util.ArrayList;
import java.util.List;

public class Technical extends Serialize{
  private List<String> format;
  private String size;
  private List<String> location;
  private List<Requirement> requirement;
  private InstallationRemarks installationRemarks;
  private OtherPlatformRequirements otherPlatformRequirements;
  private Duration duration;
  
  protected String formatName = "format";
  protected String sizeName = "size";
  protected String locationName = "location";
  protected String requirementName = "requirement";
  protected String installationRemarksName = "installationRemarks";
  protected String otherPlatformRequirementsName = "otherPlatformRequirements";
  protected String durationName = "duration";
  
  public Technical() {
    super();
  }
  
  public Technical(JSONObject json) {
    super(json, "");
  }
  
  public Technical(JSONObject json, String prefix) {
    super(json, prefix);
  }
  @Override
  protected void init() {
    String formatValue = JSONUtil.getStringValue(json, formatName);
    if (formatValue == null) {
      JSONArray formatArray = JSONUtil.getJSONArray(json, formatName);
      if (formatArray != null) {
        for (int i = 0; i < formatArray.length(); i++) {
          String object = formatArray.optString(i);
          if (object != null)
            addFormat(object);
        }
      }
    } else {
      addFormat(formatValue);
    }
    
    size = JSONUtil.getStringValue(json, sizeName);
    
    String locationValue = JSONUtil.getStringValue(json, locationName);
    if (locationValue == null) {
      JSONArray locationArray = JSONUtil.getJSONArray(json, locationName);
      if (locationArray != null) {
        for (int i = 0; i < locationArray.length(); i++) {
          String object = locationArray.optString(i);
          if (object != null)
            addLocation(object);
        }
      }
    } else {
      addLocation(locationValue);
    }
    
    JSONObject requirementValue = JSONUtil.getJSONObject(json, requirementName);
    if (requirementValue == null) {
      JSONArray requirementArray = JSONUtil.getJSONArray(json, requirementName);
      if (requirementArray != null) {
        for (int i = 0; i < requirementArray.length(); i++) {
          JSONObject object = requirementArray.optJSONObject(i);
          if (object != null)
            new Requirement(object, prefix);
        }
      }
    } else {
      addRequirement(new Requirement(requirementValue, prefix));
    }
    
    JSONObject installationJSON = JSONUtil.getJSONObject(json, installationRemarksName);
    if (installationJSON != null)
      this.installationRemarks = new InstallationRemarks(installationJSON, prefix);
    
    JSONObject otherPlatformRequirementsJSON = JSONUtil.getJSONObject(json, otherPlatformRequirementsName);
    if (otherPlatformRequirementsJSON != null)
      this.otherPlatformRequirements = new OtherPlatformRequirements(otherPlatformRequirementsJSON, prefix);
    
    JSONObject durationJSON = JSONUtil.getJSONObject(json, durationName);
    if (durationJSON != null)
      this.duration = new Duration(durationJSON, prefix);
  }

  @Override
  protected void setElementName() {
    
    
  }
  
  public List<String> getFormat() {
    return format;
  }
  
  public void addFormat(String f) {
    if (format == null) {
      format = new ArrayList<String>();
    }
    format.add(f);
  }

  public void setFormat(List<String> format) {
    this.format = format;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public List<String> getLocation() {
    return location;
  }

  public void addLocation(String l) {
    if (location == null) {
      location = new ArrayList<String>();
    }
    location.add(l);
  }
  
  public void setLocation(List<String> location) {
    this.location = location;
  }

  public List<Requirement> getRequirement() {
    return requirement;
  }

  public void setRequirement(List<Requirement> requirement) {
    this.requirement = requirement;
  }
  
  public void addRequirement(Requirement r) {
    if(requirement == null) {
      requirement = new ArrayList<Requirement>();
    }
    requirement.add(r);
  }

  public InstallationRemarks getInstallationRemarks() {
    return installationRemarks;
  }

  public void setInstallationRemarks(InstallationRemarks installationRemarks) {
    this.installationRemarks = installationRemarks;
  }

  public OtherPlatformRequirements getOtherPlatFormRequirements() {
    return otherPlatformRequirements;
  }

  public void setOtherPlatFormRequirements(
      OtherPlatformRequirements otherPlatformRequirements) {
    this.otherPlatformRequirements = otherPlatformRequirements;
  }

  public Duration getDuration() {
    return duration;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }
}
