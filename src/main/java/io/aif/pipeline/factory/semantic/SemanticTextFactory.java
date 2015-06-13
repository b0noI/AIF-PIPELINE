package io.aif.pipeline.factory.semantic;


import io.aif.language.fact.Factr;
import io.aif.language.fact.IFactDefiner;
import io.aif.language.fact.IFactQuery;
import io.aif.language.ner.NERExtractor;
import io.aif.language.semantic.SemanticGraphBuilder;
import io.aif.language.word.IWord;
import io.aif.pipeline.model.ISemanticText;
import io.aif.pipeline.model.IText;

import java.util.List;
import java.util.stream.Collectors;

class SemanticTextFactory implements ISemanticTextFactory {

    private final IText text;

    SemanticTextFactory(final IText text) {
        this.text = text;
    }

    @Override
    public ISemanticText build() {
        final List<IWord.IWordPlaceholder> placeholders = text.sentences()
                .stream()
                .flatMap(sentence -> sentence.stream())
                .collect(Collectors.toList());

        final SemanticGraphBuilder semanticGraphBuilder = new SemanticGraphBuilder();

        final Factr factr = new Factr(IFactDefiner.Type.SUPER_FACT.getInstance(), new NERExtractor());
        final IFactQuery factQuery = factr.run(text.sentences());

        return new SemanticText(semanticGraphBuilder.build(placeholders), factQuery);
    }

}
