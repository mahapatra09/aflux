package de.tum.in.aflux.component.flink.util;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.lang3.RandomStringUtils;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

public class JavaCodeGenerator {

    public static final int VARIABLES_NAME_LENGTH = 10;
    public static final String GENERATED_CLASS_NAME = "SmartSantanderJob";
    public static final String GENERATED_PACKAGE_NAME = "de.tum.in.flink";

    private CodeBlock.Builder code;
    private String outputPath;

    public JavaCodeGenerator(CodeBlock.Builder code, String outputPath) {
        this.code = code;
        this.outputPath = outputPath;
    }

    public CodeBlock.Builder getCode() {
        return code;
    }

    public void setCode(CodeBlock.Builder code) {
        this.code = code;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public void generateJavaClassFile() throws IOException {
        // create main method
        MethodSpec.Builder main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addException(Exception.class)
                .returns(void.class)
                .addParameter(String[].class, "args");

        // add code
        main.addCode(this.getCode().build());

        // generate the class
        TypeSpec GeneratedClass = TypeSpec.classBuilder(GENERATED_CLASS_NAME)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(main.build())
                .build();

        // generate the file
        JavaFile javaFile = JavaFile.builder(GENERATED_PACKAGE_NAME, GeneratedClass)
                .build();

        // output the final file
        javaFile.writeTo(new File(this.getOutputPath()));
    }

    public static String newVariableName() {
        return RandomStringUtils.randomAlphabetic(VARIABLES_NAME_LENGTH).toLowerCase();
    }

}
