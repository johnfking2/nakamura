package org.sakaiproject.nakamura.user.counts;

import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.sakaiproject.nakamura.api.lite.ClientPoolException;
import org.sakaiproject.nakamura.api.lite.Repository;
import org.sakaiproject.nakamura.api.lite.Session;
import org.sakaiproject.nakamura.api.lite.StorageClientException;
import org.sakaiproject.nakamura.api.lite.accesscontrol.AccessDeniedException;
import org.sakaiproject.nakamura.api.lite.authorizable.Authorizable;
import org.sakaiproject.nakamura.api.lite.authorizable.AuthorizableManager;
import org.sakaiproject.nakamura.api.solr.SolrServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountsRefreshJob implements Job {
  private static final Logger LOGGER = LoggerFactory.getLogger(CountsRefreshJob.class);

  Repository sparseRepository;
  SolrServerService solrServerService;
  CountProvider countProvider;

  public CountsRefreshJob(Repository sparseRepository,
      SolrServerService solrServerService, CountProvider countProvider) {
    this.sparseRepository = sparseRepository;
    this.solrServerService = solrServerService;
    this.countProvider = countProvider;
  }

  public void execute(JobContext context) {
    Session session = null;
    try {
      session = this.sparseRepository.loginAdministrative();
      AuthorizableManager authManager = session.getAuthorizableManager();
      SolrServer solrServer = solrServerService.getServer();
      Long nowTicks = System.currentTimeMillis();
      Long updateTicks = nowTicks - 10 * 1000;
      // String queryString =
      // "(+resourceType:sparse/user OR +resourceType:sparse/group) AND -countLastUpdate:[* TO *]";
      // //-countLastUpdate:[\\"\\" TO *]
      String queryString = "resourceType:authorizable AND -countLastUpdate:[* TO *]"; // -countLastUpdate:[\\"\\"
                                                                                      // TO
                                                                                      // *]
      SolrQuery solrQuery = new SolrQuery(queryString);
      solrQuery.setTermsLimit(100);
      QueryResponse response;
      try {
        response = solrServer.query(solrQuery);
        SolrDocumentList results = response.getResults();
        for (SolrDocument solrDocument : results) {
          String authorizableId = (String) solrDocument.getFieldValue("id");
          Authorizable authorizable = authManager.findAuthorizable(authorizableId);
          this.countProvider.update(authorizable);
        }
      } catch (SolrServerException e) {
        LOGGER.warn(e.getMessage(), e);
      }
    } catch (ClientPoolException e) {
      LOGGER.warn(e.getMessage(), e);
    } catch (StorageClientException e) {
      LOGGER.warn(e.getMessage(), e);
    } catch (AccessDeniedException e) {
      LOGGER.warn(e.getMessage(), e);
    } finally {
      if (session != null)
        try {
          session.logout();
        } catch (ClientPoolException e) {
          LOGGER.warn(e.getMessage(), e);
        }
    }
  }
}
