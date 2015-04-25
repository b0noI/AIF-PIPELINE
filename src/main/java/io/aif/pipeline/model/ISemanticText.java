package io.aif.pipeline.model;


import io.aif.associations.model.IGraph;
import io.aif.language.word.IWord;

public interface ISemanticText {

    public IGraph<IWord> wordsGraph();

    // TODO objects API

}
