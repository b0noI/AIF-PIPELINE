package io.aif.pipeline.factory.semantic;

import io.aif.associations.builder.AssociationGraph;
import io.aif.language.fact.IFactQuery;
import io.aif.language.word.IWord;
import io.aif.pipeline.model.ISemanticText;


class SemanticText implements ISemanticText {

    private final AssociationGraph<IWord> graph;

    private final IFactQuery factQuery;

    public SemanticText(final AssociationGraph<IWord> graph, final IFactQuery factQuery) {
        this.graph = graph;
        this.factQuery = factQuery;
    }

    @Override
    public AssociationGraph<IWord> wordsGraph() {
        return graph;
    }

    @Override
    public IFactQuery factQuery() {
        return factQuery;
    }

}
