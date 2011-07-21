package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public class Rights extends Serialize {
  private Cost cost;
  private CopyrightAndOtherRestrictions copyrightAndOtherRestrictions;
  private Description description;
  
  protected String costName = "cost";
  protected String copyrightAndOtherRestrictionsName = "copyrightAndOtherRestrictions";
  protected String descriptionName = "description";
  
  public Rights() {
    super();
  }
  
  public Rights(JSONObject json) {
    super(json, "");
  }
  
  public Rights(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  protected void init() {
    JSONObject costJSON = JSONUtil.getJSONObject(json, costName);
    if (costJSON != null) 
      cost = new Cost(costJSON, prefix);
    
    JSONObject copyrightAndOtherRestrictionsJSON = JSONUtil.getJSONObject(json, copyrightAndOtherRestrictionsName);
    if (copyrightAndOtherRestrictionsJSON != null)
      copyrightAndOtherRestrictions = new CopyrightAndOtherRestrictions(copyrightAndOtherRestrictionsJSON, prefix);
    
    JSONObject descriptionJSON = JSONUtil.getJSONObject(json, descriptionName);
    if (descriptionJSON != null) {
      description = new Description(descriptionJSON, prefix);
    }
  }

  @Override
  protected void setElementName() {
    costName = prefix + "cost";
    copyrightAndOtherRestrictionsName = prefix + "copyrightAndOtherRestrictions";
    descriptionName = prefix + "description";
  }
  
  public Cost getCost() {
    return cost;
  }

  public void setCost(Cost cost) {
    this.cost = cost;
  }

  public CopyrightAndOtherRestrictions getCopyrightAndOtherRestrictions() {
    return copyrightAndOtherRestrictions;
  }

  public void setCopyrightAndOtherRestrictions(
      CopyrightAndOtherRestrictions copyrightAndOtherRestrictions) {
    this.copyrightAndOtherRestrictions = copyrightAndOtherRestrictions;
  }

  public Description getDescription() {
    return description;
  }

  public void setDescription(Description description) {
    this.description = description;
  }
}
