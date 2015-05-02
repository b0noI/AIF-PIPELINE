package io.aif.pipeline.model;


import io.aif.associations.model.IGraph;
import io.aif.language.fact.IFact;
import io.aif.language.fact.IFactQuery;
import io.aif.language.word.IWord;

import java.util.Set;

public interface ISemanticText {

    public IGraph<IWord> wordsGraph();

    public IFactQuery factQuery();

}
