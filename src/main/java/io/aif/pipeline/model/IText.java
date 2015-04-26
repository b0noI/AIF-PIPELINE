package io.aif.pipeline.model;


import io.aif.language.sentence.separators.classificators.ISeparatorGroupsClassifier;
import io.aif.language.word.IWord;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IText {

    public List<String> tokens();

    public List<List<IWord.IWordPlaceholder>> sentences();

    public Map<ISeparatorGroupsClassifier.Group, Set<Character>> separators();

    public Set<IWord> words();

}
