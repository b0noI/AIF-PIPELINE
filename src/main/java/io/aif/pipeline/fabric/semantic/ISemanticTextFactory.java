package io.aif.pipeline.fabric.semantic;


import io.aif.pipeline.fabric.IFactory;
import io.aif.pipeline.model.ISemanticText;
import io.aif.pipeline.model.IText;

public interface ISemanticTextFactory extends IFactory<ISemanticText> {

    public static ISemanticText build(final IText text) {
        return new SemanticTextFactory(text).build();
    }

}
