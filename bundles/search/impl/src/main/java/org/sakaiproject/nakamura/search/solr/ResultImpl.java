package org.sakaiproject.nakamura.search.solr;

import org.apache.solr.common.SolrDocument;
import org.sakaiproject.nakamura.api.search.solr.Result;
import org.sakaiproject.nakamura.api.solr.SafeSolrMap;

import java.util.Collection;
import java.util.Map;

public class ResultImpl implements Result {

  private SolrDocument solrDocument;

  public ResultImpl(SolrDocument solrDocument) {
    this.solrDocument = solrDocument;
  }

  public String getPath() {
    return (String) solrDocument.getFirstValue("path");
  }

  public Map<String, Collection<Object>> getProperties() {
    return new SafeSolrMap<String, Collection<Object>>(solrDocument.getFieldValuesMap());
  }

  public Object getFirstValue(String name) {
    return solrDocument.getFirstValue(name);
  }
}
