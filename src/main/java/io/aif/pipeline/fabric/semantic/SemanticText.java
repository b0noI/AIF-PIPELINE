package io.aif.pipeline.fabric.semantic;

import io.aif.associations.model.IGraph;
import io.aif.language.fact.IFact;
import io.aif.language.fact.IFactQuery;
import io.aif.language.word.IWord;
import io.aif.pipeline.model.ISemanticText;

import java.util.Set;


class SemanticText implements ISemanticText {

    private final IGraph<IWord> graph;

    private final IFactQuery factQuery;

    public SemanticText(final IGraph<IWord> graph, final IFactQuery factQuery) {
        this.graph = graph;
        this.factQuery = factQuery;
    }

    @Override
    public IGraph<IWord> wordsGraph() {
        return graph;
    }

    @Override
    public IFactQuery factQuery() {
        return factQuery;
    }

}
