package de.tum.in.aflux.component.flink.api;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipFile;

/**
 * This class processes the source code of Flink from a .zip file located in the <pre>resources</pre> folder.
 * Then it runs JavaParser on the source files to generate a list of CompilationUnit that may later be used
 * by FlinkApiMapper.
 */
public class FlinkApiParser {

    public static List<CompilationUnit> processApiZipFile(String fileName, String[] acceptedArtifacts) throws IOException {
        // temp zip file
        File tempZipFile = File.createTempFile("flinkAPI", ".zip");
        tempZipFile.deleteOnExit();
        // read from resources, save in temporary zip file
        InputStream in = FlinkApiParser.class.getResourceAsStream("/" + fileName);
        try (FileOutputStream out = new FileOutputStream(tempZipFile)) {
            IOUtils.copy(in, out);
        }

        List<CompilationUnit> allCompilationUnits = new ArrayList<>();

        // process zip file and store CUs in list
        ZipFile zipFile = new ZipFile(tempZipFile);
        zipFile.stream()
                .filter(zipEntry -> {
                    String name = zipEntry.getName();
                    // begin conditions of files to be extracted from the .zip file
                    boolean isJavaFile = name.endsWith(".java");
                    boolean isTestFile = name.contains("/test/");
                    long belongsToAcceptedArtifact = 1;
                    if (acceptedArtifacts != null)
                        belongsToAcceptedArtifact = Arrays.stream(acceptedArtifacts)
                                .filter(artifact -> (name.contains(artifact)))
                                .count();
                    // end conditions
                    return isJavaFile && !isTestFile && belongsToAcceptedArtifact > 0;
                })
                .forEach(zipEntry -> {
                    try {
                        InputStream javaFile = zipFile.getInputStream(zipEntry);
                        CompilationUnit cu = JavaParser.parse(javaFile);
                        allCompilationUnits.add(cu);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        return allCompilationUnits;
    }

}
