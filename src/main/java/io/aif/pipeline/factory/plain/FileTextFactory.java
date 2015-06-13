package io.aif.pipeline.factory.plain;


import io.aif.pipeline.factory.semantic.ISemanticTextFactory;
import io.aif.pipeline.model.ISemanticText;
import io.aif.pipeline.model.IText;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class FileTextFactory extends AbstractTextFactory {

    private final Path path;
    private final Charset charset;

    public FileTextFactory(final Path path) {
        this.path = path;
        this.charset = Charset.forName("UTF-8");
    }

    public FileTextFactory(final Path path, Charset charset) {
        this.path = path;
        this.charset = charset;
    }

    public static void main(final String[] args) {
        final Path path = Paths.get(args[0]);
        final FileTextFactory fileTextFactory;
        if (args.length > 1) {
            final Charset encoding = Charset.forName(args[1]);
            fileTextFactory = new FileTextFactory(path, encoding);
        } else {
            fileTextFactory = new FileTextFactory(path);
        }
        System.out.println("Building TEXT");
        final IText text = fileTextFactory.build();
        System.out.println("Building semanticText");
        final ISemanticText semanticText = ISemanticTextFactory.build(text);
        semanticText.factQuery().allFacts().stream().forEach(System.out::println);

    }

    @Override
    protected String getRawText() {
        try {
            return readAllText(new FileInputStream(path.toFile()), charset);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String readAllText(final InputStream inputStream, Charset charset) throws IOException {
        try(final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {

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
