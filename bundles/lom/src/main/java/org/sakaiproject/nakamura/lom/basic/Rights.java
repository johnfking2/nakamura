package org.sakaiproject.nakamura.lom.basic;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.elements.CopyrightAndOtherRestrictions;
import org.sakaiproject.nakamura.lom.elements.Cost;
import org.sakaiproject.nakamura.lom.elements.Description;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

public class Rights extends Serialize {
  private Cost cost;
  private CopyrightAndOtherRestrictions copyrightAndOtherRestrictions;
  private Description description;

  public Rights() {
    super();
  }
  
  public Rights(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String costName = "cost";
    String copyrightAndOtherRestrictionsName = "copyrightAndOtherRestrictions";
    String descriptionName = "description";
    JSONObject costJSON = JSONUtil.getJSONObject(json, costName);
    if (costJSON != null) 
      cost = new Cost(costJSON);
    
    JSONObject copyrightAndOtherRestrictionsJSON = JSONUtil.getJSONObject(json, copyrightAndOtherRestrictionsName);
    if (copyrightAndOtherRestrictionsJSON != null)
      copyrightAndOtherRestrictions = new CopyrightAndOtherRestrictions(copyrightAndOtherRestrictionsJSON);
    
    JSONObject descriptionJSON = JSONUtil.getJSONObject(json, descriptionName);
    if (descriptionJSON != null) {
      description = new Description(descriptionJSON);
    }
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
  
  @Override
  public String generateXML() {
    StringBuilder sb = new StringBuilder("");
    if (this.getCopyrightAndOtherRestrictions() != null)
      sb.append(this.getCopyrightAndOtherRestrictions().generateXML());
    if (this.getCost() != null)
      sb.append(this.getCost().generateXML());
    if (this.getDescription() != null)
      sb.append(this.getDescription().generateXML());    
    if (sb.toString().equals(""))
      return "";
    return new String("<rights>" + sb.toString() + "</rights>");
  }
}
