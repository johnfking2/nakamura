package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

import java.util.ArrayList;
import java.util.List;

public class Resource extends Serialize {

  private List<Identifier> identifier;

  private List<Description> description;
  
  protected String identifierName = "identifier";
  protected String descriptionName = "description";
  
  public Resource() {
    super();
  }
  
  public Resource(JSONObject json) {
    super(json, "");
  }
  
  public Resource(JSONObject json, String prefix) {
    super(json, prefix);
  }

  @Override
  protected void init() {
    JSONObject idJSON = JSONUtil.getJSONObject(json, identifierName);
    if (idJSON == null) {
      JSONArray idArray = JSONUtil.getJSONArray(json, identifierName);
      if (idArray != null) {
        for (int i = 0; i < idArray.length(); i++) {
          JSONObject object = idArray.optJSONObject(i);
          if (object != null) {
            addIdentifier(new Identifier(object, prefix));
          }
        }
      }
    } else {
      addIdentifier(new Identifier(idJSON, prefix));
    }
    
    JSONObject descriptionJSON = JSONUtil.getJSONObject(json, descriptionName);
    if (descriptionJSON == null) {
      JSONArray descriptionArray = JSONUtil.getJSONArray(json, descriptionName);
      if (descriptionArray != null) {
        for (int i = 0; i < descriptionArray.length(); i++) {
          JSONObject object = descriptionArray.optJSONObject(i);
          if (object != null) {
            addDescription(new Description(object, prefix));
          }
        }
      }
    } else {
      addDescription(new Description(descriptionJSON, prefix));
    }
  }
  
  @Override
  protected void setElementName() {
    identifierName = prefix + "identifier";
    descriptionName = prefix + "description";
  }

  public List<Identifier> getIdentifier() {
    return identifier;
  }
  
  public void addIdentifier(Identifier id) {
    if (identifier == null) {
      identifier = new ArrayList<Identifier>();
    }
    identifier.add(id);
  }

  public void setIdentifier(List<Identifier> identifier) {
    this.identifier = identifier;
  }

  public List<Description> getDescription() {
    return description;
  }
  
  public void addDescription(Description d) {
    if (description == null) {
      description = new ArrayList<Description> ();
    }
    description.add(d);
  }

  public void setDescription(List<Description> description) {
    this.description = description;
  }
}