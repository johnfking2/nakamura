package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

import java.util.ArrayList;
import java.util.List;

public class TaxonPath extends Serialize {

  private Source source;
  private List<Taxon> taxon;

  public TaxonPath() {
    super();
  }
  
  public TaxonPath(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String sourceName = "source";
    String taxonName = "taxon";
    JSONObject sourceJSON = JSONUtil.getJSONObject(json, sourceName);
    if (sourceJSON != null) 
      source = new Source(sourceJSON);
    
    JSONObject taxonJSON = JSONUtil.getJSONObject(json, taxonName);
    if (taxonJSON == null) {
      JSONArray taxonArray = JSONUtil.getJSONArray(json, taxonName);
      if (taxonArray != null) {
        for (int i = 0; i < taxonArray.length(); i++) {
          JSONObject object = taxonArray.optJSONObject(i);
          if (object != null) {
            addTaxon(new Taxon(object));
          }
        }
      }
    } else {
      addTaxon(new Taxon(taxonJSON));
    }
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
