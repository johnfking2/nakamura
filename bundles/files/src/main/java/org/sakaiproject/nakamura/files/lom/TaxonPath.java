package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

import java.util.ArrayList;
import java.util.List;

public class TaxonPath extends Serialize {

  private Source source;
  private List<Taxon> taxon;
  
  protected String sourceName = "source";
  protected String taxonName = "taxon";
  
  public TaxonPath() {
    super();
  }
  
  public TaxonPath(JSONObject json) {
    super(json, "");
  }
  
  public TaxonPath(JSONObject json, String prefix) {
    super(json, prefix);
  }

  @Override
  protected void init() {
    JSONObject sourceJSON = JSONUtil.getJSONObject(json, sourceName);
    if (sourceJSON != null) 
      source = new Source(sourceJSON, prefix);
    
    JSONObject taxonJSON = JSONUtil.getJSONObject(json, taxonName);
    if (taxonJSON == null) {
      JSONArray taxonArray = JSONUtil.getJSONArray(json, taxonName);
      if (taxonArray != null) {
        for (int i = 0; i < taxonArray.length(); i++) {
          JSONObject object = taxonArray.optJSONObject(i);
          if (object != null) {
            addTaxon(new Taxon(object, prefix));
          }
        }
      }
    } else {
      addTaxon(new Taxon(taxonJSON, prefix));
    }
  }
  
  @Override
  protected void setElementName() {
    sourceName = prefix + "source";
    taxonName = prefix + "taxon";
  }
 
  public Source getSource() {
    return source;
  }

  public void setSource(Source source) {
    this.source = source;
  }

  public List<Taxon> getTaxon() {
    return taxon;
  }
  
  public void addTaxon(Taxon t) {
    if (taxon == null) 
      taxon = new ArrayList<Taxon> ();
    taxon.add(t);
  }

  public void setTaxon(List<Taxon> taxon) {
    this.taxon = taxon;
  }


}
