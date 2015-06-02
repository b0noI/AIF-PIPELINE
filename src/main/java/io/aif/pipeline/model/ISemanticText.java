package io.aif.pipeline.model;


import io.aif.associations.builder.AssociationGraph;
import io.aif.language.fact.IFactQuery;
import io.aif.language.word.IWord;

public interface ISemanticText {

    public AssociationGraph<IWord> wordsGraph();

    public IFactQuery factQuery();

}
