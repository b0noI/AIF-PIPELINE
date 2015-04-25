package io.aif.pipeline.fabric.semantic;

import io.aif.associations.model.IGraph;
import io.aif.language.word.IWord;
import io.aif.pipeline.model.ISemanticText;


class SemanticText implements ISemanticText {

    private final IGraph<IWord> graph;

    public SemanticText(final IGraph<IWord> graph) {
        this.graph = graph;
    }

    @Override
    public IGraph<IWord> wordsGraph() {
        return graph;
    }

}
