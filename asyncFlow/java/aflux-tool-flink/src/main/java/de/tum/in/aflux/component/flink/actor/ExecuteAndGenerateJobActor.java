package de.tum.in.aflux.component.flink.actor;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.component.flink.util.FlinkFlowMessage;
import de.tum.in.aflux.component.flink.util.JavaCodeGenerator;
import de.tum.in.aflux.component.flink.util.MavenUtils;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * This actor is in charge of generating the code to execute the job. This is also the step in which all the code is
 * written to the final source file and where maven is run to generate the final executable .jar file (which
 * could be uploaded to a cluster running Flink).
 * Example output:
 *
 * <pre>
 *     env.execute();
 * </pre>
 */
public class ExecuteAndGenerateJobActor extends AbstractAFluxActor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public ExecuteAndGenerateJobActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String,String> properties) {
        super(fluxId, fluxEnvironment, fluxRunner, properties, -1);
    }

    @Override
    protected void runCore(Object message) throws Exception {

        // Validate and cast message
        FlinkFlowMessage msg;
        try {
            msg = FlinkFlowMessage.fromRawMessage(message);
        } catch(IllegalArgumentException e) {
            this.sendOutput("Error when receiving message from previous node.");
            return;
        }
        CodeBlock.Builder code = msg.getCode();
        TypeName inputType = msg.getCurrentType();
        String inputVariableName = msg.getCurrentDataStreamVariableName();

        // Add code: execute job
        this.sendOutput("Generating code for: job execution");
        code.addStatement("$L.$L()",
                EnvironmentSetUpActor.GENERATED_CODE_VARIABLE_ENV,
                API.getMethodName("StreamExecutionEnvironment.execute"));

        // Compute paths
        String outputPath = System.getProperty("user.home"); // to extract template
        String projectPath = String.join(File.separator,
                outputPath, MavenUtils.FLINK_TEMPLATE_PATH); // to access POM
        String codePath = String.join(File.separator,
                projectPath, "src", "main", "java"); // to output code

        // Extract the project template
        extractTemplate(outputPath);

        // Generate class code
        JavaCodeGenerator codeGen = new JavaCodeGenerator(code, codePath);
        codeGen.generateJavaClassFile();
        this.sendOutput("Generating final Flink code");

        // Generate packaged jar
        this.sendOutput("Generating final packaged Flink job");
        String result = "";
        try {
            result = MavenUtils.runMavenCommand("clean package", projectPath);
        } catch(Exception e) {
            this.sendOutput("An error occurred when building with Maven");
        } finally {
            this.sendOutput(result);
        }
    }

    public void extractTemplate(String outputPath) throws URISyntaxException {
        // retrieve location of current code
        URI sourcePath = getClass().getProtectionDomain().getCodeSource().getLocation().toURI();

        // extract template
        try {
            MavenUtils.extractTemplate(sourcePath, outputPath);
            this.sendOutput("Flink project template extracted successfully.");
        } catch (IOException e) {
            this.sendOutput("Error while extracting Flink project template.");
        }
    }

}