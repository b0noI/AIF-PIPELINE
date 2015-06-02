package io.aif.pipeline.factory.plain;


import io.aif.language.sentence.separators.classificators.ISeparatorGroupsClassifier;
import io.aif.language.word.IWord;
import io.aif.pipeline.model.IText;

import java.util.List;
import java.util.Map;
import java.util.Set;

class Text implements IText {

    private final List<String> tokens;

    private final List<List<IWord.IWordPlaceholder>> sentences;

    private final Map<ISeparatorGroupsClassifier.Group, Set<Character>> separators;

    private final Set<IWord> words;

    Text(final List<String> tokens,
         final List<List<IWord.IWordPlaceholder>> sentences,
         final Map<ISeparatorGroupsClassifier.Group, Set<Character>> separators,
         final Set<IWord> words) {
        this.tokens = tokens;
        this.sentences = sentences;
        this.separators = separators;
        this.words = words;
    }

    @Override
    public List<String> tokens() {
        return tokens;
    }

    @Override
    public List<List<IWord.IWordPlaceholder>> sentences() {
        return sentences;
    }

    @Override
    public Map<ISeparatorGroupsClassifier.Group, Set<Character>> separators() {
        return separators;
    }

    @Override
    public Set<IWord> words() {
        return words;
    }

}
