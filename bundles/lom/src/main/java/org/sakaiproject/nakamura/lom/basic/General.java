package org.sakaiproject.nakamura.lom.basic;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.elements.AggregationLevel;
import org.sakaiproject.nakamura.lom.elements.Coverage;
import org.sakaiproject.nakamura.lom.elements.Description;
import org.sakaiproject.nakamura.lom.elements.Identifier;
import org.sakaiproject.nakamura.lom.elements.Keyword;
import org.sakaiproject.nakamura.lom.elements.Structure;
import org.sakaiproject.nakamura.lom.elements.Title;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

import java.util.ArrayList;
import java.util.List;

public class General extends Serialize{
  private List<Identifier> identifier;
  private Title title;
  private List<Description> description;
  private List<String> language;
  private List<Keyword> keyword;
  private List<Coverage> coverage;
  private Structure structure;
  private AggregationLevel aggregationLevel;

  public General() {
    super();
  }
  
  public General(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String identifierName = "identifier";
    String titleName = "title";
    String languageName = "language";
    String descriptionName = "description";
    String keywordName = "keyword";
    String coverageName = "coverage";
    String structureName = "structure";
    String aggregationLevelName = "aggregationLevel";
    JSONObject identifierJSON = JSONUtil.getJSONObject(json, identifierName);
    if (identifierJSON == null) {
      JSONArray identifierArray = JSONUtil.getJSONArray(json, identifierName);
      if (identifierArray != null) {
        for (int i = 0; i < identifierArray.length(); i++) {
          JSONObject object = identifierArray.optJSONObject(i);
          if (object != null) {
            Identifier iden = new Identifier(object);
            addIdentifier(iden);
          }
        }
      }
    } else {
      Identifier iden = new Identifier(identifierJSON);
      addIdentifier(iden);
    }
    
    JSONObject titleJSON = JSONUtil.getJSONObject(json, titleName);
    if (titleJSON != null) {
      title = new Title(titleJSON);
    }
    
    JSONObject descriptionJSON = JSONUtil.getJSONObject(json, descriptionName);
    if (descriptionJSON == null) {
      JSONArray descriptionArray = JSONUtil.getJSONArray(json, descriptionName);
      if (descriptionArray != null) {
        for (int i = 0; i < descriptionArray.length(); i++) {
          JSONObject object = descriptionArray.optJSONObject(i);
          if (object != null) {
            Description des = new Description(object);
            addDescription(des);
          }
        }
      }
    } else {
      Description d = new Description(descriptionJSON);
      addDescription(d);
    }
    
    String languageValue = JSONUtil.getStringValue(json, languageName);
    if (languageValue == null) {
      JSONArray languageArray = JSONUtil.getJSONArray(json, languageName);
      if (languageArray != null) {
        for (int i = 0; i < languageArray.length(); i++) {
          String lang = languageArray.optString(i);
          if (lang != null) 
            addLanguage(lang);
        }
      }
    } else {
      addLanguage(languageValue);
    }
    
    JSONObject keywordJSON = JSONUtil.getJSONObject(json, keywordName);
    if (keywordJSON == null) {
      JSONArray keywordArray = JSONUtil.getJSONArray(json, keywordName);
      if (keywordArray != null) {
        for (int i = 0; i < keywordArray.length(); i++) {
          JSONObject object = keywordArray.optJSONObject(i);
          if (object != null) {
            Keyword key = new Keyword(object);
            addKeyword(key);
          }
        }
      }
    } else {
      Keyword key = new Keyword(keywordJSON);
      addKeyword(key);
    }
    
    JSONObject coverageJSON = JSONUtil.getJSONObject(json, coverageName);
    if (coverageJSON == null) {
      JSONArray coverageArray = JSONUtil.getJSONArray(json, coverageName);
      if (coverageArray != null) {
        for (int i = 0; i < coverageArray.length(); i++) {
          JSONObject object = coverageArray.optJSONObject(i);
          if (object != null) {
            Coverage cover = new Coverage(object);
            addCoverage(cover);
          }
        }
      }
    } else {
      Coverage cover = new Coverage(coverageJSON);
      addCoverage(cover);
    }
    
    JSONObject structureJSON = JSONUtil.getJSONObject(json, structureName);
    if (structureJSON != null) {
      structure = new Structure(structureJSON);
    }
    
    JSONObject aggregationLevelJSON = JSONUtil.getJSONObject(json, aggregationLevelName);
    if (aggregationLevelJSON != null) {
      aggregationLevel = new AggregationLevel(aggregationLevelJSON);
    }
  }
 
  public List<Identifier> getIdentifier() {
    return identifier;
  }
  
  public void addIdentifier(Identifier i) {
    if (identifier == null) {
      identifier = new ArrayList<Identifier>();
    }
    identifier.add(i);
  }

  public void setIdentifier(List<Identifier> identifier) {
    this.identifier = identifier;
  }

  public Title getTitle() {
    return title;
  }

  public void setTitle(Title title) {
    this.title = title;
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
    if (language == null) {
      language = new ArrayList<String>();
    }
  }
  
  public void setLanguage(List<String> language) {
    this.language = language;
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

  public List<Coverage> getCoverage() {
    return coverage;
  }

  public void addCoverage(Coverage c) {
    if (coverage == null)
      coverage = new ArrayList<Coverage> ();
    coverage.add(c);
  }
  public void setCoverage(List<Coverage> coverage) {
    this.coverage = coverage;
  }

  public Structure getStructure() {
    return structure;
  }

  public void setStructure(Structure structure) {
    this.structure = structure;
  }

  public AggregationLevel getAggregationLevel() {
    return aggregationLevel;
  }

  public void setAggregationLevel(AggregationLevel aggregationLevel) {
    this.aggregationLevel = aggregationLevel;
  }
}
