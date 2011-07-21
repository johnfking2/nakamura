package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

import java.util.ArrayList;
import java.util.List;

public class Educational extends Serialize {
  private InteractivityType interactivityType;
  private List<LearningResourceType> learningResourceType;
  private InteractivityLevel interactivityLevel;
  private SemanticDensity semanticDensity;
  private List<IntendedEndUserRole> intendedEndUserRole;
  private List<Context> context;
  private List<TypicalAgeRange> typicalAgeRange;
  private Difficulty difficulty;
  private TypicalLearningTime typicalLearningTime;
  private List<Description> description;
  private List<String> language;

  protected String interactivityTypeName = "interactivityType";
  protected String learningResourceTypeName = "learningResourceType";
  protected String interactivityLevelName = "interactivityLevel";
  protected String semanticDensityName = "semanticDensity";
  protected String intendedEndUserRoleName = "intendedEndUserRole";
  protected String contextName = "context";
  protected String typicalAgeRangeName = "typicalAgeRange";
  protected String difficultyName = "difficulty";
  protected String typicalLearningTimeName = "typicalLearningTime";
  protected String descriptionName = "description";
  protected String languageName = "language";
  
  public Educational() {
    super();
  }
  
  public Educational(JSONObject json) {
    super(json, "");
  }
  
  public Educational(JSONObject json, String prefix) {
    super(json, prefix);
  }
  @Override
  protected void init() {
    JSONObject interactivityTypeJSON = JSONUtil.getJSONObject(json, interactivityTypeName);
    if (interactivityTypeJSON != null) {
      interactivityType = new InteractivityType(interactivityTypeJSON, prefix);
    } 
    
    JSONObject learningResourceTypeJSON = JSONUtil.getJSONObject(json, learningResourceTypeName);
    if (learningResourceTypeJSON == null) {
      JSONArray learningResourceTypeArray = JSONUtil.getJSONArray(json, learningResourceTypeName);
      if (learningResourceTypeArray != null) {
        for (int i = 0; i < learningResourceTypeArray.length(); i++){
          JSONObject object = learningResourceTypeArray.optJSONObject(i);
          if (object != null) {
            addLearningResourceType(new LearningResourceType(object, prefix));
          }
            
        }
      }
    } else {
      addLearningResourceType(new LearningResourceType(learningResourceTypeJSON, prefix));
    }
    
    JSONObject interactivityLevelJSON = JSONUtil.getJSONObject(json, interactivityLevelName);
    if (interactivityLevelJSON != null) {
      interactivityLevel = new InteractivityLevel(interactivityLevelJSON, prefix);
    }
    
    JSONObject semanticDensityJSON = JSONUtil.getJSONObject(json, semanticDensityName);
    if (semanticDensityJSON != null) {
      semanticDensity = new SemanticDensity(semanticDensityJSON, prefix);
    }
    
    JSONObject intendedEndUserRoleJSON = JSONUtil.getJSONObject(json, intendedEndUserRoleName);
    if (intendedEndUserRoleJSON == null) {
      JSONArray intendedEndUserRoleArray = JSONUtil.getJSONArray(json, intendedEndUserRoleName);
      if (intendedEndUserRoleArray != null) {
        for (int i = 0; i < intendedEndUserRoleArray.length(); i++){
          JSONObject object = intendedEndUserRoleArray.optJSONObject(i);
          if (object != null) {
            addIntendedEndUserRole(new IntendedEndUserRole(object, prefix));
          }
            
        }
      }
    } else {
      addIntendedEndUserRole(new IntendedEndUserRole(intendedEndUserRoleJSON, prefix));
    }
    
    JSONObject contextJSON = JSONUtil.getJSONObject(json, contextName);
    if (intendedEndUserRoleJSON == null) {
      JSONArray contextArray = JSONUtil.getJSONArray(json, contextName);
      if (contextArray != null) {
        for (int i = 0; i < contextArray.length(); i++){
          JSONObject object = contextArray.optJSONObject(i);
          if (object != null) {
            addContext(new Context(object, prefix));
          }
            
        }
      }
    } else {
      addContext(new Context(contextJSON, prefix));
    }
    
    JSONObject typicalAgeRangeJSON = JSONUtil.getJSONObject(json, typicalAgeRangeName);
    if (typicalAgeRangeJSON == null) {
      JSONArray typicalAgeRangeArray = JSONUtil.getJSONArray(json, typicalAgeRangeName);
      if (typicalAgeRangeArray != null) {
        for (int i = 0; i < typicalAgeRangeArray.length(); i++){
          JSONObject object = typicalAgeRangeArray.optJSONObject(i);
          if (object != null) {
            addTypicalAgeRange(new TypicalAgeRange(object, prefix));
          }
            
        }
      }
    } else {
      addTypicalAgeRange(new TypicalAgeRange(typicalAgeRangeJSON, prefix));
    }
    
    JSONObject difficultyJSON = JSONUtil.getJSONObject(json, difficultyName);
    if (difficultyJSON != null) {
      difficulty = new Difficulty(difficultyJSON, prefix);
    }

    JSONObject typicalLearningTimeJSON = JSONUtil.getJSONObject(json, typicalLearningTimeName);
    if (typicalLearningTimeJSON != null) {
      typicalLearningTime = new TypicalLearningTime(typicalLearningTimeJSON, prefix);
    }
    
    JSONObject descriptionJSON = JSONUtil.getJSONObject(json, descriptionName);
    if (descriptionJSON == null) {
      JSONArray descriptionArray = JSONUtil.getJSONArray(json, descriptionName);
      if (descriptionArray != null) {
        for (int i = 0; i < descriptionArray.length(); i++){
          JSONObject object = descriptionArray.optJSONObject(i);
          if (object != null) {
            addDescription(new Description(object, prefix));
          }
            
        }
      }
    } else {
      addDescription(new Description(descriptionJSON, prefix));
    }
    
    String languageJSON = JSONUtil.getStringValue(json, languageName);
    if (languageJSON == null) {
      JSONArray languageArray = JSONUtil.getJSONArray(json, languageName);
      if (languageArray != null) {
        for (int i = 0; i < languageArray.length(); i++){
          String object = languageArray.optString(i);
          if (object != null) {
            addLanguage(object);
          }
            
        }
      }
    } else {
      addLanguage(languageJSON);
    }
  }

  @Override
  protected void setElementName() {
    interactivityTypeName = prefix + "interactivityType";
    learningResourceTypeName = prefix + "learningResourceType";
    interactivityLevelName = prefix + "interactivityLevel";
    semanticDensityName = prefix + "semanticDensity";
    intendedEndUserRoleName = prefix + "intendedEndUserRole";
    contextName = prefix + "context";
    typicalAgeRangeName = prefix + "typicalAgeRange";
    difficultyName = prefix + "difficulty";
    typicalLearningTimeName = prefix + "typicalLearningTime";
    descriptionName = prefix + "description";
    languageName = prefix + "language";
  }
  
  public InteractivityType getInteractivityType() {
    return interactivityType;
  }
  
  public void setInteractivityType(InteractivityType interactivityType) {
    this.interactivityType = interactivityType;
  }

  public List<LearningResourceType> getLearningResourceType() {
    return learningResourceType;
  }
  
  public void addLearningResourceType(LearningResourceType l) {
    if (learningResourceType == null) {
      learningResourceType = new ArrayList<LearningResourceType>();
    }
    learningResourceType.add(l);
  }

  public void setLearningResourceType(List<LearningResourceType> learningResourceType) {
    this.learningResourceType = learningResourceType;
  }

  public InteractivityLevel getInteractivityLevel() {
    return interactivityLevel;
  }

  public void setInteractivityLevel(InteractivityLevel interactivityLevel) {
    this.interactivityLevel = interactivityLevel;
  }

  public SemanticDensity getSemanticDensity() {
    return semanticDensity;
  }

  public void setSemanticDensity(SemanticDensity semanticDensity) {
    this.semanticDensity = semanticDensity;
  }

  public List<IntendedEndUserRole> getIntendedEndUserRole() {
    return intendedEndUserRole;
  }
  
  public void addIntendedEndUserRole(IntendedEndUserRole i) {
    if (intendedEndUserRole == null) {
      intendedEndUserRole = new ArrayList<IntendedEndUserRole>();
    }
    intendedEndUserRole.add(i);
  }

  public void setIntendedEndUserRole(List<IntendedEndUserRole> intendedEndUserRole) {
    this.intendedEndUserRole = intendedEndUserRole;
  }

  public List<Context> getContext() {
    return context;
  }
  
  public void addContext(Context c) {
    if (context == null) {
      context = new ArrayList<Context>();
    }
    context.add(c);
  }

  public void setContext(List<Context> context) {
    this.context = context;
  }

  public List<TypicalAgeRange> getTypicalAgeRange() {
    return typicalAgeRange;
  }
  
  public void addTypicalAgeRange(TypicalAgeRange t) {
    if (typicalAgeRange == null) {
      typicalAgeRange = new ArrayList<TypicalAgeRange>();
    }
    typicalAgeRange.add(t);
  }

  public void setTypicalAgeRange(List<TypicalAgeRange> typicalAgeRange) {
    this.typicalAgeRange = typicalAgeRange;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  public TypicalLearningTime getTypicalLearningTime() {
    return typicalLearningTime;
  }

  public void setTypicalLearningTime(TypicalLearningTime typicalLearningTime) {
    this.typicalLearningTime = typicalLearningTime;
  }

  public List<Description> getDescription() {
    return description;
  }
  
  public void addDescription(Description d) {
    if (description == null) {
      description = new ArrayList<Description> ();
    }
    description.add(d);
  }

  public void setDescription(List<Description> description) {
    this.description = description;
  }

  public List<String> getLanguage() {
    return language;
  }

  public void addLanguage(String l) {
    if (language == null){
      language = new ArrayList<String>();
    }
    language.add(l);
  }
  
  public void setLanguage(List<String> language) {
    this.language = language;
  }
}
