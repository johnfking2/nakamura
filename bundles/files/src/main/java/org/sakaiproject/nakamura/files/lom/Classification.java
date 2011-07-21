package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

import java.util.ArrayList;
import java.util.List;

public class Classification extends Serialize {
  private Purpose purpose;
  private List<TaxonPath> taxonPath;
  private Description description;
  private List<Keyword> keyword;

  protected String purposeName = "purpose";
  protected String taxonPathName = "taxonPath";
  protected String descriptionName = "description";
  protected String keywordName = "keyword";
  
  
  public Classification() {
    super();
  }
  
  public Classification(JSONObject json) {
    super(json, "");
  }
  
  public Classification(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  protected void init() {
    JSONObject purposeJSON = JSONUtil.getJSONObject(json, purposeName);
    if (purposeJSON != null) {
      purpose = new Purpose(purposeJSON, prefix);
    }
    
    JSONObject taxonPathJSON = JSONUtil.getJSONObject(json, taxonPathName);
    if (taxonPathJSON == null) {
      JSONArray taxonPathArray = JSONUtil.getJSONArray(json, taxonPathName);
      if (taxonPathArray != null) {
        for (int i = 0; i < taxonPathArray.length(); i++) {
          JSONObject object = taxonPathArray.optJSONObject(i);
          if (object != null)
            addTaxonPath(new TaxonPath(object, prefix));
        }
      }
    } else {
      addTaxonPath(new TaxonPath(taxonPathJSON, prefix));
    }
    
    JSONObject descriptionJSON = JSONUtil.getJSONObject(json, descriptionName);
    if (descriptionJSON != null) {
      description = new Description(descriptionJSON, prefix);
    }
    
    JSONObject keywordJSON = JSONUtil.getJSONObject(json, keywordName);
    if (keywordJSON != null) {
      addKeyword(new Keyword(keywordJSON, prefix));
    } else {
      JSONArray keywordArray = JSONUtil.getJSONArray(json, keywordName);
      if (keywordArray != null) {
        for (int i = 0; i < keywordArray.length(); i++) {
          JSONObject object = keywordArray.optJSONObject(i);
          if (object != null) {
            addKeyword(new Keyword(object, prefix));
          }
        }
      }
    }

  }

  @Override
  protected void setElementName() {
    purposeName = prefix + "purpose";
    taxonPathName = prefix + "taxonPath";
    descriptionName = prefix + "description";
    keywordName = prefix + "keyword";
  }
  
  public Purpose getPurpose() {
    return purpose;
  }

  public void setPurpose(Purpose purpose) {
    this.purpose = purpose;
  }

  public List<TaxonPath> getTaxonPath() {
    return taxonPath;
  }
  
  public void addTaxonPath (TaxonPath tp) {
    if (taxonPath == null)
      taxonPath = new ArrayList<TaxonPath>();
    taxonPath.add(tp);
  }

  public void setTaxonPath(List<TaxonPath> taxonPath) {
    this.taxonPath = taxonPath;
  }

  public Description getDescription() {
    return description;
  }

  public void setDescription(Description description) {
    this.description = description;
  }

  public List<Keyword> getKeyword() {
    return keyword;
  }
  
  public void addKeyword(Keyword k) {
    if (keyword == null) 
      keyword = new ArrayList<Keyword>();
    keyword.add(k);
  }

  public void setKeyword(List<Keyword> keyword) {
    this.keyword = keyword;
  }
}
