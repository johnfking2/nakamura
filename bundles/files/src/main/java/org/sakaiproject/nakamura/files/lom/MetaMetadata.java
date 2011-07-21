package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

import java.util.ArrayList;
import java.util.List;

public class MetaMetadata extends Serialize {

  private List<Identifier> identifier;
  private List<Contribute> contribute;
  private List<String> metadataSchema;
  private String language;
  
  protected String identifierName = "identifier";
  protected String contributeName = "contribute";
  protected String metadataSchemaName = "metadataSchema";
  protected String languageName = "language";
  
  public MetaMetadata() {
    super();
  }
  
  public MetaMetadata(JSONObject json) {
    super(json, "");
  }
  
  public MetaMetadata(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  protected void init() {
    JSONObject identifierJSON = JSONUtil.getJSONObject(json, identifierName);
    if (identifierJSON == null) {
      JSONArray identifierArray = JSONUtil.getJSONArray(json, identifierName);
      if (identifierArray != null) {
        for (int i = 0; i < identifierArray.length(); i++) {
          JSONObject object = identifierArray.optJSONObject(i);
          if (object != null){
            addIdentifier(new Identifier(object, prefix));
          }
        }
      }
    } else {
      addIdentifier(new Identifier(identifierJSON, prefix));
    }
    
    JSONObject contributeJSON = JSONUtil.getJSONObject(json, contributeName);
    if (contributeJSON == null) {
      JSONArray contributeArray = JSONUtil.getJSONArray(json, contributeName);
      if (contributeArray != null) {
        for (int i = 0; i < contributeArray.length(); i++) {
          JSONObject object = contributeArray.optJSONObject(i);
          if (object != null){
            addContribute(new Contribute(object, prefix, Contribute.CONTRIBUTETYPE.METAMETADATA));
          }
        }
      }
    } else {
      addContribute(new Contribute(contributeJSON, prefix, Contribute.CONTRIBUTETYPE.METAMETADATA));
    }
    
    String metadataSchemaJSON = JSONUtil.getStringValue(json, metadataSchemaName);
    if (metadataSchemaJSON == null) {
      JSONArray metadataSchemaArray = JSONUtil.getJSONArray(json, metadataSchemaName);
      if (metadataSchemaArray != null) {
        for (int i = 0; i < metadataSchemaArray.length(); i++) {
          String object = metadataSchemaArray.optString(i);
          if (object != null){
            addMetadataSchema(object);
          }
        }
      }
    } else {
      addMetadataSchema(metadataSchemaJSON);
    }
    
    language = JSONUtil.getStringValue(json, languageName);
  }

  @Override
  protected void setElementName() {
    identifierName = prefix + "identifier";
    contributeName = prefix + "contribute";
    metadataSchemaName = prefix + "metadataSchema";
    languageName = prefix + "language";
  }
  
  public List<Identifier> getIdentifier() {
    return identifier;
  }
  
  public void addIdentifier(Identifier i) {
    if (identifier == null) {
      identifier = new ArrayList<Identifier>();
    }
    identifier.add(i);
  }

  public void setIdentifier(List<Identifier> identifier) {
    this.identifier = identifier;
  }

  public List<Contribute> getContribute() {
    return contribute;
  }
  
  public void addContribute(Contribute c) {
    if (contribute == null) {
      contribute = new ArrayList<Contribute>();
    }
    contribute.add(c);
  }

  public void setContribute(List<Contribute> contribute) {
    this.contribute = contribute;
  }

  public List<String> getMetadataSchema() {
    return metadataSchema;
  }

  public void addMetadataSchema(String m) {
    if (metadataSchema == null) {
      metadataSchema = new ArrayList<String>();
    }
    metadataSchema.add(m);
  }
  
  public void setMetadataSchema(List<String> metadataSchema) {
    this.metadataSchema = metadataSchema;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

}
