package io.aif.pipeline.factory.plain;

import io.aif.language.common.IDict;
import io.aif.language.common.IDictBuilder;
import io.aif.language.common.IMapper;
import io.aif.language.common.ISearchable;
import io.aif.language.sentence.separators.classificators.ISeparatorGroupsClassifier;
import io.aif.language.sentence.separators.extractors.ISeparatorExtractor;
import io.aif.language.sentence.separators.groupers.ISeparatorsGrouper;
import io.aif.language.sentence.splitters.AbstractSentenceSplitter;
import io.aif.language.token.TokenSplitter;
import io.aif.language.word.IWord;
import io.aif.language.word.dict.DictBuilder;
import io.aif.language.word.dict.WordPlaceHolderMapper;
import io.aif.pipeline.model.IText;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

abstract class AbstractTextFactory implements ITextFactory {

    @Override
    public IText build() {
        final String text = getRawText();

        final TokenSplitter tokenSplitter = new TokenSplitter();
        final AbstractSentenceSplitter sentenceSplitter = AbstractSentenceSplitter.Type.HEURISTIC.getInstance();
        final List<String> tokens = tokenSplitter.split(text);
        final List<List<String>> rawSentences = sentenceSplitter.split(tokens);
        final List<String> filteredTokens = rawSentences.stream().flatMap(List::stream).collect(Collectors.toList());

        final IDictBuilder dictBuilder = new DictBuilder();
        final IDict<IWord> dict = dictBuilder.build(filteredTokens);
        final IMapper<Collection<String>, List<IWord.IWordPlaceholder>> toWordPlaceHolderMapper = new WordPlaceHolderMapper((ISearchable<String, IWord>)dict);
        final List<List<IWord.IWordPlaceholder>> sentences = rawSentences.stream()
                .map(toWordPlaceHolderMapper::map)
                .collect(Collectors.toList());

        final ISeparatorExtractor testInstance = ISeparatorExtractor.Type.PROBABILITY.getInstance();
        final ISeparatorsGrouper separatorsGrouper = ISeparatorsGrouper.Type.PROBABILITY.getInstance();
        final ISeparatorGroupsClassifier sentenceSeparatorGroupsClassificatory = ISeparatorGroupsClassifier.Type.PROBABILITY.getInstance();
        final List<Character> separators = testInstance.extract(tokens).get();
        final Map<ISeparatorGroupsClassifier.Group, Set<Character>> grouppedSeparators = sentenceSeparatorGroupsClassificatory.classify(tokens, separatorsGrouper.group(tokens, separators));

        return new Text(tokens, sentences, grouppedSeparators, dict.getWords());
    }

    abstract protected String getRawText();

}
