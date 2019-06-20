package de.tum.in.aflux.component.flink.util;

import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Includes several functions aimed at operating with the Flink project template.
 *
 * @author federicofdez
 */
public final class MavenUtils {

    /**
     * Location of the Maven executable in the system.
     */
    public static final String MAVEN_HOME_PATH = "/opt/local/share/java/maven32/";

    /**
     * Base path of the Flink template files, under the <i>resources</i> folder.
     */
    public static final String FLINK_TEMPLATE_PATH = "flink-template";

    /**
     * Extract template for Flink project from the current source code and place it
     * somewhere else in the system. This is especially required when running aflux-
     * tool-flink as a JAR file.
     *
     * @param sourcePath URI specifying the source path of the JAR file.
     * @param destinationPath String specifying the destination path for the extracted files.
     */
    public static void extractTemplate(URI sourcePath, String destinationPath) throws IOException {
        final File jarFile = new File(sourcePath);
        StringBuilder result = new StringBuilder();
        result.append("Path for extracted files: ")
                .append(destinationPath)
                .append("\n");

        final JarFile jar = new JarFile(jarFile);
        final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
        while(entries.hasMoreElements()) {
            final JarEntry file = entries.nextElement();
            if (file.getName().startsWith(FLINK_TEMPLATE_PATH + "/")) { //filter according to the path
                File f = new File(destinationPath + File.separator + file.getName());
                result.append("Extracted: ")
                        .append(file.getName())
                        .append("\n");
                if (file.isDirectory()) { // if its a directory, create it
                    f.mkdir();
                    continue;
                }
                InputStream is = jar.getInputStream(file); // get the input stream
                FileOutputStream fos = new FileOutputStream(f);
                while (is.available() > 0) {  // write contents of 'is' to 'fos'
                    fos.write(is.read());
                }
                fos.close();
                is.close();
            }
        }
        jar.close();
    }

    /**
     * Execute a Maven command.
     *
     * @param mavenCommand Maven goal to be run.
     * @param pomFilePath Path of the POM file.
     * @return output of the execution.
     */
    public static String runMavenCommand(String mavenCommand, String pomFilePath) throws MavenInvocationException, IllegalStateException {

        // create request to be executed
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File(pomFilePath));
        request.setGoals(Collections.singletonList(mavenCommand));

        // create invoker
        Invoker invoker = new DefaultInvoker();

        // prepare output parser
        final StringBuilder mavenOutput = new StringBuilder();
        invoker.setOutputHandler(new InvocationOutputHandler() {
            public void consumeLine(String line) {
                mavenOutput.append(line).append(System.lineSeparator());
            }
        });

        // specify maven home path
        invoker.setMavenHome(new File(MAVEN_HOME_PATH));

        // run command
        InvocationResult invocationResult = invoker.execute(request);
        if (invocationResult.getExitCode() != 0) {
            throw new IllegalStateException( "Build failed." );
        }

        return mavenOutput.toString();
    }

}