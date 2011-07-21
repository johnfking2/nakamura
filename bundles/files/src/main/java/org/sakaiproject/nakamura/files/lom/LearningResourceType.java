package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class LearningResourceType extends VocabularyType {
  
  private String[] vocabulary = new String[] {"exercise", "simulation", "questionnaire", "diagram", "figure",
      "graph", "index", "slide", "table", "narrative text", "exam", "experiment", "problem statement", 
      "self assessment", "lecture"};

  public LearningResourceType() {
    super();
  }
  
  public LearningResourceType(JSONObject json) {
    super(json, "");
  }
  
  public LearningResourceType(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
