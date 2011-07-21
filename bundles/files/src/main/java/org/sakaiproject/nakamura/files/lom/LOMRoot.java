package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

import java.util.ArrayList;
import java.util.List;

public class LOMRoot extends Serialize{
  private General general;
  private LifeCycle lifeCycle;
  private Technical technical;
  private MetaMetadata metaMetadata;
  private List<Educational> educational;
  private Rights rights;
  private List<Relation> relation;
  private List<Annotation> annotation;
  private List<Classification> classification;
  
  protected String lomName = "lom";
  protected String generalName = "general";
  protected String lifeCycleName = "lifeCycle";
  protected String technicalName = "technical";
  protected String metaMetadataName = "metaMetadata";
  protected String educationalName = "educational";
  protected String rightsName = "rights";
  protected String relationName = "relation";
  protected String annotationName = "annotation";
  protected String classificationName = "classification";
  
  public LOMRoot () {
    super();
  }
  
  public LOMRoot(JSONObject json) {
    super(json, "");
  }
  
  public LOMRoot(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  protected void init() {
    JSONObject generalJSON = JSONUtil.getJSONObject(json, generalName);
    if (generalJSON != null)
      general = new General(generalJSON, prefix);
    
    JSONObject lifeCycleObject = JSONUtil.getJSONObject(json, lifeCycleName);
    if (lifeCycleObject != null){
      lifeCycle = new LifeCycle(lifeCycleObject, prefix);
    }
    
    JSONObject technicalJSON = JSONUtil.getJSONObject(json, technicalName);
    if (technicalJSON != null)
      technical = new Technical(technicalJSON, prefix);
    
    JSONObject metaMetadataJSON = JSONUtil.getJSONObject(json, metaMetadataName);
    if (metaMetadataJSON != null) {
      metaMetadata = new MetaMetadata(metaMetadataJSON, prefix);
    }
    
    JSONObject educationalJSON = JSONUtil.getJSONObject(json, educationalName);
    if (educationalJSON == null) {
      JSONArray educationalArray = JSONUtil.getJSONArray(json, educationalName);
      if (educationalArray != null){
        for (int i = 0; i < educationalArray.length(); i++) {
          JSONObject object = educationalArray.optJSONObject(i);
          if (object != null) {
            Educational e = new Educational(object, prefix);
            addEducational(e);
          }
        }
      }
    } else {
      addEducational(new Educational(educationalJSON, prefix));
    }
    
    JSONObject rightsJSON = JSONUtil.getJSONObject(json, rightsName);
    if (rightsJSON != null)
      rights = new Rights(rightsJSON, prefix);
    
    JSONObject relationJSON = JSONUtil.getJSONObject(json, relationName);
    if (relationJSON == null) {
      JSONArray relationArray = JSONUtil.getJSONArray(json, relationName);
      if (relationArray != null) {
        for (int i = 0; i < relationArray.length(); i++) {
          JSONObject object = relationArray.optJSONObject(i);
          if (object != null)
            addRelation(new Relation(object, prefix));
        }
      }
    } else {
      addRelation(new Relation(relationJSON, prefix));
    }
    
    JSONObject annotationJSON = JSONUtil.getJSONObject(json, annotationName);
    if (annotationJSON == null) {
      JSONArray annotationArray = JSONUtil.getJSONArray(json, annotationName);
      if (annotationArray != null) {
        for (int i = 0; i < annotationArray.length(); i++) {
          JSONObject object = annotationArray.optJSONObject(i);
          if (object != null)
            addAnnotation(new Annotation(object, prefix));
        }
      }
    } else {
      addAnnotation(new Annotation(annotationJSON, prefix));
    }
    
    JSONObject classificationJSON = JSONUtil.getJSONObject(json, classificationName);
    if (classificationJSON == null) {
      JSONArray classificationArray = JSONUtil.getJSONArray(json, classificationName);
      if (classificationArray != null) {
        for (int i = 0; i < classificationArray.length(); i++) {
          JSONObject object = classificationArray.optJSONObject(i);
          if (object != null)
            addClassification(new Classification(object, prefix));
        }
      }
    } else {
      addClassification(new Classification(classificationJSON, prefix));
    }
  }
  
  @Override
  protected void setElementName() {
    lomName = prefix + "lom";
    generalName = prefix + "general";
    lifeCycleName = prefix + "lifeCycle";
    technicalName = prefix + "technical";
    metaMetadataName = prefix + "metaMetadata";
    educationalName = prefix + "educational";
    rightsName = prefix + "rights";
    annotationName = prefix + "annotation";
    classificationName = prefix + "classification";
    relationName = prefix + "relation";
  }
  
  public JSONObject getJson() {
    return json;
  }

  public void setJson(JSONObject json) {
    this.json = json;
  }
  
  public General getGeneral() {
    return general;
  }
  
  public void setGeneral(General general) {
    this.general = general;
  }
  
  public LifeCycle getLifeCycle() {
    return lifeCycle;
  }

  public void setLifeCycle(LifeCycle lifeCycle) {
    this.lifeCycle = lifeCycle;
  }

  public Technical getTechnical() {
    return technical;
  }

  public void setTechnical(Technical technical) {
    this.technical = technical;
  }
  
  public MetaMetadata getMetaMetadata() {
    return metaMetadata;
  }

  public void setMetaMetadata(MetaMetadata metaMetadata) {
    this.metaMetadata = metaMetadata;
  }

  public List<Educational> getEducational() {
    return educational;
  }

  public void addEducational(Educational e) {
    if (educational == null)
      educational = new ArrayList<Educational>();
    educational.add(e);
  }
  
  public void setEducational(List<Educational> educational) {
    this.educational = educational;
  }

  public Rights getRights() {
    return rights;
  }

  public void setRights(Rights rights) {
    this.rights = rights;
  }

  public List<Relation> getRelation() {
    return relation;
  }
  
  public void addRelation(Relation r) {
    if (relation == null)
      relation = new ArrayList<Relation>();
    relation.add(r);
  }

  public void setRelation(List<Relation> relation) {
    this.relation = relation;
  }

  public List<Annotation> getAnnotation() {
    return annotation;
  }

  public void addAnnotation(Annotation a) {
    if (annotation == null) {
      annotation = new ArrayList<Annotation> ();
    }
    annotation.add(a);
  }
  
  public void setAnnotation(List<Annotation> annotation) {
    this.annotation = annotation;
  }

  public List<Classification> getClassification() {
    return classification;
  }
  
  public void addClassification(Classification c) {
    if (classification == null) {
      classification = new ArrayList<Classification>();
    }
    classification.add(c);
  }

  public void setClassification(List<Classification> classification) {
    this.classification = classification;
  }
}
