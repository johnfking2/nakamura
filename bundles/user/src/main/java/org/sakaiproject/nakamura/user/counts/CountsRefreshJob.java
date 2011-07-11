package org.sakaiproject.nakamura.user.counts;

import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.sakaiproject.nakamura.api.lite.Repository;
import org.sakaiproject.nakamura.api.solr.SolrServerService;

public class CountsRefreshJob implements Job {
  
  Repository sparseRepository;
  SolrServerService solrServerService;
  
  public CountsRefreshJob(Repository sparseRepository, SolrServerService solrServerService) {
    this.sparseRepository = sparseRepository;
    this.solrServerService = solrServerService;
  }

  public void execute(JobContext context) {
    SolrServer solrServer = solrServerService.getServer();
    try {
      
      solrServer.query(null);
    } catch (SolrServerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
