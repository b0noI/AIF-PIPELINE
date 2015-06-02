package io.aif.pipeline.fabric.plain;


import io.aif.pipeline.fabric.semantic.ISemanticTextFactory;
import io.aif.pipeline.model.ISemanticText;
import io.aif.pipeline.model.IText;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTextFactory extends AbstractTextFactory {

    private final Path path;

    public FileTextFactory(final Path path) {
        this.path = path;
    }

    public static void main(final String[] args) {
        final Path path = Paths.get(args[0]);
        final FileTextFactory fileTextFactory = new FileTextFactory(path);
        System.out.println("Building TEXT");
        final IText text = fileTextFactory.build();
        System.out.println("Building semanticText");
        final ISemanticText semanticText = ISemanticTextFactory.build(text);
        semanticText.factQuery().allFacts().stream().forEach(System.out::println);

    }

    @Override
    protected String getRawText() {
        try {
            return readAllText(new FileInputStream(path.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String readAllText(final InputStream inputStream) throws IOException {
        try(final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            final StringBuffer buff = new StringBuffer();

            String line = null;

            while((line = reader.readLine()) != null) {
                buff.append(line + System.lineSeparator());
            }

            return buff.toString();
        } catch (NullPointerException e) {
            throw e;
        }
    }

}
