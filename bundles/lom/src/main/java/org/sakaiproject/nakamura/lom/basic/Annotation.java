package org.sakaiproject.nakamura.lom.basic;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.elements.Date;
import org.sakaiproject.nakamura.lom.elements.Description;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

public class Annotation extends Serialize{

  private String entity;
  private Date date;
  private Description description;
  
  public Annotation() {
    super();
  }
  
  public Annotation(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String entityName = "entity";
    String dateName = "date";
    String descriptionName = "description";
    entity = JSONUtil.getStringValue(json, entityName);
    
    JSONObject dateJSON = JSONUtil.getJSONObject(json, dateName);
    if (dateJSON != null) {
      date = new Date(dateJSON);
    }
    
    JSONObject desJSON = JSONUtil.getJSONObject(json, descriptionName);
    if (desJSON != null) {
      description = new Description(desJSON);
    }
  }

  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Description getDescription() {
    return description;
  }

  public void setDescription(Description description) {
    this.description = description;
  }
}
