package org.sakaiproject.nakamura.files.cp;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;

import java.util.ArrayList;
import java.util.List;

public class Resource extends HasMetadata {
  private List<File> files;
  private List<Dependency> dependencies;
  private String type;
  private String xmlBase;
  private String href;
  
  protected String fileName = "file";
  protected String dependencyName = "dependency";
  protected String typeName = "type";
  protected String xmlBaseName = "xmlbase";
  protected String hrefName = "href";
  
  public Resource() {
    super();
  }
  
  public Resource(JSONObject json) {
    this.json = json;
    super.setElementName();
    this.setElementName();
    super.init();
    this.init();
  }
  
  public Resource (JSONObject json, String prefix, String lomPrefix) {
    this.json = json;
    this.prefix = prefix;
    this.lomPrefix = lomPrefix;
    super.setElementName();
    this.setElementName();
    super.init();
    this.init();
  }
  
  public void init() {
    JSONObject fileJSON = JSONUtil.getJSONObject(json, fileName);
    if (fileJSON == null) {
      JSONArray fileArray = JSONUtil.getJSONArray(json, fileName);
      for (int i = 0; i < fileArray.length(); i++) {
        JSONObject object = fileArray.optJSONObject(i);
        if (object != null)
          addFile(new File(object, prefix, lomPrefix));
      }
    } else {
      addFile(new File(fileJSON, prefix, lomPrefix));
    }
    
    JSONObject dependencyJSON = JSONUtil.getJSONObject(json, dependencyName);
    if (dependencyJSON == null) {
      JSONArray dependencyArray = JSONUtil.getJSONArray(json, dependencyName);
      if (dependencyArray != null) {
        for (int i = 0; i < dependencyArray.length(); i++) {
          JSONObject object = dependencyArray.optJSONObject(i);
          if (object != null)
            addDependency(new Dependency(object, prefix));
        }
      }
    } else {
      addDependency(new Dependency(dependencyJSON, prefix));
    }
    
    type = JSONUtil.getStringValue(json, typeName);
    xmlBase = JSONUtil.getStringValue(json, xmlBaseName);
    href = JSONUtil.getStringValue(json,hrefName);
  }
  
  public void setElementName() {
    fileName = prefix + "file";
    dependencyName = prefix + "dependency";
    typeName = prefix + "type";
    xmlBaseName = prefix + "xmlbase";
    hrefName = prefix + "href";
  }
  
  public List<File> getFiles() {
    return files;
  }
  
  public void addFile(File f) {
    if (files == null) {
      files = new ArrayList<File>();
    }
    files.add(f);
  }
  
  public void addFile(File f, int index) {
    if (files == null) {
      files = new ArrayList<File> ();
    }
    files.add(index, f);
  }
  
  public void setFiles(List<File> files) {
    this.files = files;
  }
  
  public List<Dependency> getDependencies() {
    return dependencies;
  }
  
  public void addDependency(Dependency d) {
    if (dependencies == null)
      dependencies = new ArrayList<Dependency>();
    dependencies.add(d);
  }
  
  public void setDependencies(List<Dependency> dependencies) {
    this.dependencies = dependencies;
  }
  
  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public String getXmlBase() {
    return xmlBase;
  }
  
  public void setXmlBase(String xmlBase) {
    this.xmlBase = xmlBase;
  }
  
  public String getHref() {
    return href;
  }
  
  public void setHref(String href) {
    this.href = href;
  }
}
