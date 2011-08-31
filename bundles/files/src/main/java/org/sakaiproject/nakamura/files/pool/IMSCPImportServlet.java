package org.sakaiproject.nakamura.files.pool;

import static org.apache.sling.jcr.resource.JcrResourceConstants.SLING_RESOURCE_TYPE_PROPERTY;
import static org.sakaiproject.nakamura.api.files.FilesConstants.POOLED_CONTENT_CREATED_FOR;
import static org.sakaiproject.nakamura.api.files.FilesConstants.POOLED_CONTENT_FILENAME;
import static org.sakaiproject.nakamura.api.files.FilesConstants.POOLED_CONTENT_RT;
import static org.sakaiproject.nakamura.api.files.FilesConstants.POOLED_CONTENT_USER_MANAGER;
import static org.sakaiproject.nakamura.api.files.FilesConstants.POOLED_NEEDS_PROCESSING;
import static org.sakaiproject.nakamura.api.files.FilesConstants.POOLED_CONTENT_CUSTOM_MIMETYPE;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.io.JSONWriter;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.json.xml.XML;
import org.osgi.service.event.EventAdmin;
import org.sakaiproject.nakamura.api.cluster.ClusterTrackingService;
import org.sakaiproject.nakamura.api.doc.BindingType;
import org.sakaiproject.nakamura.api.doc.ServiceBinding;
import org.sakaiproject.nakamura.api.doc.ServiceDocumentation;
import org.sakaiproject.nakamura.api.doc.ServiceExtension;
import org.sakaiproject.nakamura.api.doc.ServiceMethod;
import org.sakaiproject.nakamura.api.doc.ServiceResponse;
import org.sakaiproject.nakamura.api.files.FilesConstants;
import org.sakaiproject.nakamura.api.lite.ClientPoolException;
import org.sakaiproject.nakamura.api.lite.Repository;
import org.sakaiproject.nakamura.api.lite.Session;
import org.sakaiproject.nakamura.api.lite.StorageClientException;
import org.sakaiproject.nakamura.api.lite.StorageClientUtils;
import org.sakaiproject.nakamura.api.lite.accesscontrol.AccessControlManager;
import org.sakaiproject.nakamura.api.lite.accesscontrol.AccessDeniedException;
import org.sakaiproject.nakamura.api.lite.accesscontrol.AclModification;
import org.sakaiproject.nakamura.api.lite.accesscontrol.Permissions;
import org.sakaiproject.nakamura.api.lite.accesscontrol.Security;
import org.sakaiproject.nakamura.api.lite.authorizable.Authorizable;
import org.sakaiproject.nakamura.api.lite.authorizable.AuthorizableManager;
import org.sakaiproject.nakamura.api.lite.authorizable.Group;
import org.sakaiproject.nakamura.api.lite.authorizable.User;
import org.sakaiproject.nakamura.api.lite.content.Content;
import org.sakaiproject.nakamura.api.lite.content.ContentManager;
import org.sakaiproject.nakamura.api.lite.jackrabbit.JackrabbitSparseUtils;
import org.sakaiproject.nakamura.api.user.UserConstants;
import org.sakaiproject.nakamura.files.cp.File;
import org.sakaiproject.nakamura.files.cp.HasItem;
import org.sakaiproject.nakamura.files.cp.Item;
import org.sakaiproject.nakamura.files.cp.Manifest;
import org.sakaiproject.nakamura.files.cp.Organization;
import org.sakaiproject.nakamura.files.cp.Resource;
import org.sakaiproject.nakamura.files.lom.Keyword;
import org.sakaiproject.nakamura.files.lom.LOMRoot;
import org.sakaiproject.nakamura.files.lomcp.ManifestErrorException;
import org.sakaiproject.nakamura.util.ActivityUtils;
import org.sakaiproject.nakamura.util.ExtendedJSONWriter;
import org.sakaiproject.nakamura.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.jcr.RepositoryException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

@SlingServlet (methods = "POST", paths = "/system/pool/importimscp")
@Properties(value = {
    @Property(name = "service.vendor", value = "The Sakai Foundation"),
    @Property(name = "service.description", value = "Allows for uploading IMS CP files to the pool.") })
@ServiceDocumentation(name="Import IMS CP file Servlet",
    description="Import and Updates IMS CP files in the pool",
    shortDescription="Import and Updates IMS CP files in the pool",
    bindings=@ServiceBinding(type=BindingType.PATH,bindings={"/system/pool/importimscp"},
    extensions=@ServiceExtension(name="*", description="If an extension is provided it is assumed to be the PoolID which is to be updated.")),
    methods=@ServiceMethod(name="POST",
        description={"A normal CP file post. If this is to create files, each file in the multipart file will create a new file in the pool. If a PoolID is supplied only the first file in the upload is used to overwrite the file." +
            "If versioning is required, then a POST must be performed to /p/poolID.save ",
            "Example<br>" +
            "<pre>A Multipart file upload to http://localhost:8080/system/pool/importimscp will create one Pool file per file in the upload</pre>",
            "Example<br>" +
            "<pre>A Multipart file upload to http://localhost:8080/system/pool/importimscp.3sd23a4QW4WD will update the file content for PoolID 3sd23a4QW4WD </pre>",
            "Response is of the form " +
            "<pre>" +
            "   { \"file1\" : \"3sd23a4QW4WD\", \"file2\" : \"3sd23a4QW4ZS\" } " +
            "</pre>"
          },
          response={
          @ServiceResponse(code=201,description="Where files are created"),
          @ServiceResponse(code=400,description="Where the request is invalid"),
          @ServiceResponse(code=200,description="Where the file is updated"),
          @ServiceResponse(code=500,description="Failure with HTML explanation.")}

        ))
public class IMSCPImportServlet extends SlingAllMethodsServlet {

  private static final long serialVersionUID = -1869653859810977731L;

  private static final char ALTERNATIVE_STREAM_SELECTOR_SEPARATOR = '-';
  
  @Reference
  protected ClusterTrackingService clusterTrackingService;
  
  @Reference
  protected Repository sparseRepository;

  @Reference
  protected EventAdmin eventAdmin; 

  private static final Logger LOGGER = LoggerFactory
      .getLogger(CreateContentPoolServlet.class);
  
  /**
   * Property which stores ims cp's content
   */
  public static final String IMS_CONTENT_PACKAGING = "imscp:content";
  
  @Override
  protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
      throws ServletException, IOException {
    String userId = request.getRemoteUser();

    RequestPathInfo rpi = request.getRequestPathInfo();
    String poolId = rpi.getExtension();
    String[] selectors = rpi.getSelectors();
    String alternativeStream = null;
    if ( selectors != null && selectors.length > 0 ) {
      alternativeStream = poolId;
      poolId = selectors[0];
    }
    javax.jcr.Session jcrSession = request.getResourceResolver().adaptTo(javax.jcr.Session.class);
    Session session;
    try {
      session = JackrabbitSparseUtils.getSparseSession(jcrSession);
    } catch (RepositoryException e) {
      throw new ServletException(e.getMessage(), e);
    }

    // Anonymous users cannot upload files.
    if (UserConstants.ANON_USERID.equals(userId)) {
      response.sendError(HttpServletResponse.SC_FORBIDDEN,
          "Anonymous users cannot upload files to the pool.");
      return;
    }

    Session adminSession = null;
    try {
      // Grab an admin session so we can create files in the pool space.
      adminSession = sparseRepository.loginAdministrative();
      AuthorizableManager authorizableManager = adminSession.getAuthorizableManager();
      // We need the authorizable for the user node that we'll create under the file.
      Authorizable au = authorizableManager.findAuthorizable(userId);

      // Loop over all the parameters
      // All the ones that are files will be stored.
      int statusCode = HttpServletResponse.SC_BAD_REQUEST;
      Map<String, Object> results = new HashMap<String, Object>();
      for (Entry<String, RequestParameter[]> e : request.getRequestParameterMap()
          .entrySet()) {
        for (RequestParameter p : e.getValue()) {
          if (!p.isFormField()) {
            // This is a file upload.
            // Generate an ID and store it.
            if ( poolId == null ) {
              String createPoolId = generatePoolId();
              results.put("_contentItem", ImmutableMap.of("poolId", (Object)createPoolId,  "item", createCourse(createPoolId, null, adminSession, p, request, au, true).getProperties()));
              statusCode = HttpServletResponse.SC_CREATED;
            } else {
              // Add it to the map so we can output something to the UI.
              results.put("_contentItem", ImmutableMap.of("poolId", (Object)poolId,  "item", createCourse(poolId, alternativeStream, session, p, request, au, false).getProperties()));
              statusCode = HttpServletResponse.SC_OK;
              break;
            }

          }
        }
      }
      
      System.out.println("==========================================================");
      System.out.println(results.get("_contentItem"));
      System.out.println("==========================================================");

      // Make sure we're outputting proper json.
      if ( statusCode == HttpServletResponse.SC_BAD_REQUEST ) {
        response.setStatus(statusCode);
      } else {
        response.setStatus(statusCode);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        JSONWriter jsonWriter = new JSONWriter(response.getWriter());
        
        ExtendedJSONWriter.writeValueMap(jsonWriter, results);
      }
    } catch (NoSuchAlgorithmException e) {
      LOGGER.warn(e.getMessage(), e);
      throw new ServletException(e.getMessage(), e);
    } catch (ClientPoolException e) {
      LOGGER.warn(e.getMessage(), e);
      throw new ServletException(e.getMessage(), e);
    } catch (StorageClientException e) {
      LOGGER.warn(e.getMessage(), e);
      throw new ServletException(e.getMessage(), e);
    } catch (AccessDeniedException e) {
      LOGGER.warn(e.getMessage(), e);
      throw new ServletException(e.getMessage(), e);
    } catch (JSONException e) {
      LOGGER.warn(e.getMessage(), e);
      throw new ServletException(e.getMessage(), e);
    } catch (ManifestErrorException e) {
      LOGGER.warn(e.getMessage(), e);
      throw new ServletException(e.getMessage(), e);
    } finally {
      // Make sure we're logged out.
      try {
        if ( adminSession != null ) {
          adminSession.logout();
        }
      } catch (ClientPoolException e) {
        LOGGER.warn(e.getMessage(), e);
      }
    }
  }

  private Content createCourse(String poolId, String alternativeStream, Session session, RequestParameter value,
      SlingHttpServletRequest request, Authorizable au, boolean create) throws IOException, AccessDeniedException,
      StorageClientException, JSONException, ServletException, ManifestErrorException {
    // Get the content type.
    String contentType = getContentType(value);
    if (!"application/zip".equalsIgnoreCase(contentType)) {
      throw new ServletException("The imported course file type isn't zip format", new Exception("The imported course file type isn't zip format"));
    }
    ContentManager contentManager = session.getContentManager();
    AccessControlManager accessControlManager = session.getAccessControlManager();
    if ( create ) {
      // Create a proper nt:file node in jcr with some properties on it to make it possible
      // to locate this pool file without having to use the path.
      Map<String, Object> contentProperties = new HashMap<String, Object>();
   //   contentProperties.put(POOLED_CONTENT_FILENAME, value.getFileName());
      contentProperties.put(SLING_RESOURCE_TYPE_PROPERTY, POOLED_CONTENT_RT);
      contentProperties.put(POOLED_CONTENT_CREATED_FOR, au.getId());
   //   contentProperties.put(POOLED_NEEDS_PROCESSING, "true");
   //   contentProperties.put(Content.MIMETYPE_FIELD, contentType);
      contentProperties.put(POOLED_CONTENT_USER_MANAGER, new String[]{au.getId()});
      contentProperties.put(POOLED_CONTENT_CUSTOM_MIMETYPE, "x-sakai/document");
      Content content = new Content(poolId,contentProperties);
      contentManager.update(content);
      content = contentManager.get(poolId);
      
      final ZipInputStream zin = new ZipInputStream(value.getInputStream());
      ZipEntry entry;
      String baseDir = poolId;
      String filename = "imsmanifest.xml";
      //To record whether there is manifest.xml.
      boolean flag = false; 
      HashMap<String, String> fileContent = new HashMap<String, String>();
      Manifest manifest = new Manifest();
      while ((entry = zin.getNextEntry()) != null) {
        if (filename.equalsIgnoreCase(entry.getName())) {
          BufferedReader reader = new BufferedReader(new InputStreamReader(new InputStream () {
            public int read() {
              try {
                return zin.read();
              } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
                return -1;
              }
            }
          }));
          StringBuilder builder = new StringBuilder();
          char[] chars = new char[40960];
          int length = 0;
          while (0 < (length = reader.read(chars))) {
            if (length < 40960)
              length--;
            builder.append(chars, 0, length);
          }
          
          JSONObject json = XML.toJSONObject(builder.toString());
          manifest = new Manifest(json, "", "imsmd:");
          try {
            String courseName = manifest.getMetadata().getLom().getGeneral().getTitle().getLangString().getString();
            content.setProperty(POOLED_CONTENT_FILENAME, courseName);
          } catch (Exception e) { }
          if (!contentProperties.containsKey(POOLED_CONTENT_FILENAME)) {
            contentProperties.put(POOLED_CONTENT_FILENAME, value.getFileName().substring(0, value.getFileName().lastIndexOf('.')));
          }
          flag = true;
          LOGGER.info("imsmanifest file excuted" + baseDir + "/" + filename);
          contentManager.writeBody(baseDir + "/" + filename, new ByteArrayInputStream(builder.toString().getBytes()));
          
          continue;
        }
        String entryType = getServletContext().getMimeType(entry.getName());
        if (entryType != null && entryType.contains("text")) {
          BufferedReader reader = new BufferedReader(new InputStreamReader(new InputStream () {
            public int read() {
              try {
                return zin.read();
              } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
                return -1;
              }
            }
          }));
          StringBuilder builder = new StringBuilder();
          char[] chars = new char[4096];
          int length = 0;
          while (0 < (length = reader.read(chars))) {
            builder.append(chars, 0, length);
          }
          fileContent.put(entry.getName(), builder.toString());
       //   contentManager.writeBody(baseDir + "/" + entry.getName(), new ByteArrayInputStream(builder.toString().getBytes()));
          continue;
        }
        LOGGER.info("file excuted" + baseDir + "/" + entry.getName());
        contentManager.writeBody(baseDir + "/" + entry.getName(), new InputStream() {
          public int read() {
            try {
              return zin.read();
            } catch (IOException e) {
              LOGGER.warn(e.getMessage(), e);
              return -1;
            }
          }
        });
        
      }
      zin.closeEntry();
      zin.close();
      if (!flag) {
        throw new ServletException("There is no manifest file in the course.", new Exception("There is no manifest file in the course."));
      }
      
      JSONObject pageSetJSON = manifestToPageSet(manifest, poolId, fileContent);
      
      Iterator<String> keys = pageSetJSON.keys();
      while (keys.hasNext()) {
        String key = keys.next();
        content.setProperty(key, pageSetJSON.optString(key));
      }
      contentManager.update(content);

      ActivityUtils.postActivity(eventAdmin, au.getId(), poolId, "Content", "default", "pooled content", "UPDATED_CONTENT", null);

      // deny anon everyting
      // deny everyone everything
      // grant the user everything.
      List<AclModification> modifications = new ArrayList<AclModification>();
      AclModification.addAcl(false, Permissions.ALL, User.ANON_USER, modifications);
      AclModification.addAcl(false, Permissions.ALL, Group.EVERYONE, modifications);
      AclModification.addAcl(true, Permissions.CAN_MANAGE, au.getId(), modifications);
      accessControlManager.setAcl(Security.ZONE_CONTENT, poolId, modifications.toArray(new AclModification[modifications.size()]));
    } else if (alternativeStream != null && alternativeStream.indexOf("-") > 0) {
      String[] alternativeStreamParts = StringUtils.split(alternativeStream, ALTERNATIVE_STREAM_SELECTOR_SEPARATOR);
      String pageId = alternativeStreamParts[0];
      String previewSize = alternativeStreamParts[1];
      Content alternativeContent = new Content(poolId+"/"+pageId,
        ImmutableMap.of(Content.MIMETYPE_FIELD, (Object)contentType, SLING_RESOURCE_TYPE_PROPERTY, POOLED_CONTENT_RT));
      contentManager.update(alternativeContent);
      contentManager.writeBody(alternativeContent.getPath(), value.getInputStream(), previewSize);
      ActivityUtils.postActivity(eventAdmin, au.getId(), poolId, "Content", "default", "pooled content", "UPDATED_FILE", null);
    } else {
      Content content = contentManager.get(poolId);
      content.setProperty(StorageClientUtils.getAltField(Content.MIMETYPE_FIELD, alternativeStream), contentType);
      contentManager.update(content);
      contentManager.writeBody(poolId, value.getInputStream(),alternativeStream);
      ActivityUtils.postActivity(eventAdmin, au.getId(), poolId, "Content", "default", "pooled content", "UPDATED_FILE", null);
    }
    return contentManager.get(poolId);
  }

  /** 
   * Get the content type of a file that's in a {@link RequestParameter}.
   *
   * @param value
   *          The request parameter.
   * @return The content type.
   */
  private String getContentType(RequestParameter value) {
    String contentType = value.getContentType();
    if (contentType != null) {
      int idx = contentType.indexOf(';');
      if (idx > 0) {
        contentType = contentType.substring(0, idx);
      }
    }
    if (contentType == null || contentType.equals("application/octet-stream")) {
      // try to find a better content type
      contentType = getServletContext().getMimeType(value.getFileName());
      if (contentType == null || contentType.equals("application/octet-stream")) {
        contentType = "application/octet-stream";
      }
    }
    return contentType;
  }

  private String generatePoolId() throws UnsupportedEncodingException,
      NoSuchAlgorithmException {
    return clusterTrackingService.getClusterUniqueId();
  }
  
  private JSONObject manifestToPageSet(Manifest manifest, String poolId, HashMap<String, String> fileContent) throws JSONException {
    JSONObject pages = new JSONObject();
    List<Organization> orgs = manifest.getOrganizations().getOrganizations();
    int index = 0;
    String description = "";
    JSONArray keywords = new JSONArray();
    JSONArray allResources = new JSONArray();
    try {
      description = manifest.getMetadata().getLom().getGeneral().getDescription().get(0).getLangString().getString();
      List<Keyword> keys = manifest.getMetadata().getLom().getGeneral().getKeyword();
      if (keys != null) {
        for (Keyword key : keys)
          keywords.put(key.getLangString().getString());
      }
    } catch (Exception e) { }
    pages.put("sakai:description", description);
    if (keywords.length() != 0)
      pages.put("sakai:tags", keywords);
    if (orgs != null && orgs.size() != 0) {
      for (int i = 0; i < orgs.size(); i++) {
        Vector<Item> items = getLeafItems(orgs.get(i));
        JSONObject orgJSON = new JSONObject();
        JSONObject itemJSON = new JSONObject();
        String itemID = "";
        for (int j = 0; j < items.size(); j++) {
          itemJSON = new JSONObject();
          Item item = items.get(j);
          if (item != null) {
            Resource res = manifest.getResources().searchResource(item.getIdentifierRef());
            if (res != null && res.getFiles() != null && fileContent.containsKey(res.getHref())) {
       //       itemID = StorageClientUtils.getInternalUuid();
              itemID = "id" + String.valueOf(100000 + index);
              itemJSON.put("_id", itemID);
              itemJSON.put("_title", item.getTitle());
              itemJSON.put("_order", index++);
              itemJSON.put("_canEdit", false);
              itemJSON.put("_canSubedit", false);
              itemJSON.put("_poolpath", "/p/" + poolId);
              JSONObject resourceJSON = new JSONObject();
       //       String resID = StorageClientUtils.insecureHash(res.getHref());
              String resID = "id" + String.valueOf(200000 + index);
              resourceJSON.put("_id", resID);
              resourceJSON.put("_path", poolId + "/" + res.getHref());
              
              String contentType = getServletContext().getMimeType(res.getHref());
              if (contentType == null)
                contentType = "application/octet-stream";
              resourceJSON.put("_mimeType", contentType);
              JSONArray fileArray = new JSONArray();
              for (int k = 0; k < res.getFiles().size(); k++) {
                File f = res.getFiles().get(k);
                fileArray.put(k, poolId + "/" + f.getHref());
              }
              resourceJSON.put("_dependencyPaths", fileArray);
              resourceJSON.put("page", fileContent.get(res.getHref()));
            //  pages.put("res" + resID, resourceJSON);
              itemJSON.put("_ref", resID);
             // JSONObject resource = new JSONObject();
             // resource.put(resID, resourceJSON);
              allResources.put(resourceJSON);
              JSONObject mainObject = new JSONObject(itemJSON, new String[] {"_title", "_ref", "_canEdit", "_canSubedit", "_poolpath"});
              mainObject.put("_order", 0);
              mainObject.put("_id", "main");
              mainObject.put("_elements", new JSONArray());
              if (index > 1) {
                mainObject.put("_childCount", 0);
                itemJSON.put("_childCount", 1);
              }
              itemJSON.put("main", mainObject);
              JSONArray elementsArray = new JSONArray();
              elementsArray.put(mainObject);
              itemJSON.put("_elements", elementsArray);
              if (!"".equals(itemID))
                orgJSON.put(itemID, itemJSON);
            }
          }
        }
        pages.put("structure" + i, orgJSON);
      }
      pages.put("resources", allResources);
    }
    return pages; 
  }
  
  private static Vector<Item> getLeafItems(HasItem org) {
    Vector<Item> result = new Vector<Item>();
    
    for (Item item : org.getItems()) {
      if (item.hasSubItems()) {
        result.addAll(getLeafItems(item));
      } else {
        result.add(item);
      }
    }
    return result;
  }
}
