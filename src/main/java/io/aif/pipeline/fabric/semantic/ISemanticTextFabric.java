package io.aif.pipeline.fabric.semantic;


import io.aif.pipeline.fabric.IFabric;
import io.aif.pipeline.model.ISemanticText;
import io.aif.pipeline.model.IText;

public interface ISemanticTextFabric extends IFabric<ISemanticText> {

    public static ISemanticText build(final IText text) {
        return new SemanticTextFabric(text).build();
    }

}
