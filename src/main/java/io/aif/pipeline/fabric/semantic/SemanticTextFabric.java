package io.aif.pipeline.fabric.semantic;


import io.aif.language.semantic.SemanticGraphBuilder;
import io.aif.language.word.IWord;
import io.aif.pipeline.model.ISemanticText;
import io.aif.pipeline.model.IText;

import java.util.List;
import java.util.stream.Collectors;

class SemanticTextFabric implements ISemanticTextFabric {

    private final IText text;

    SemanticTextFabric(final IText text) {
        this.text = text;
    }

    @Override
    public ISemanticText build() {
        final List<IWord.IWordPlaceholder> placeholders = text.sentences()
                .stream()
                .flatMap(sentence -> sentence.stream())
                .collect(Collectors.toList());

        final SemanticGraphBuilder semanticGraphBuilder = new SemanticGraphBuilder();
        return new SemanticText(semanticGraphBuilder.build(placeholders));
    }

}
