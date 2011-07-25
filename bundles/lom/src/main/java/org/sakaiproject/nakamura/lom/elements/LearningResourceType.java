package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class LearningResourceType extends VocabularyType {
  
  private String[] vocabulary = new String[] {"exercise", "simulation", "questionnaire", "diagram", "figure",
      "graph", "index", "slide", "table", "narrative text", "exam", "experiment", "problem statement", 
      "self assessment", "lecture"};

  public LearningResourceType() {
    super();
  }
  
  public LearningResourceType(JSONObject json) {
    super(json);
  }
  
  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }

}
