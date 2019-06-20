package de.tum.in.aflux.component.flink.actor;

import com.squareup.javapoet.*;
import de.tum.in.aflux.component.flink.Node07OutputResult;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.component.flink.util.FlinkFlowMessage;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

import javax.lang.model.element.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This actor is in charge of generating the code to output a DataStream.
 * Current supported outputs are <pre>std.out</pre>, <pre>CSV file</pre> and a <pre>kafka topic</pre>.
 * Example output:
 *
 * <pre>
 *     dataStream.print();
 *     dataStream.map(new MapFunction<Double, Tuple1<Double>>() {
 *             @Override
 *             public Tuple1<Double> map(Double aDouble) throws Exception {
 *                 return Tuple1.of(aDouble);
 *             }
 *         }).writeAsCsv("fileName.csv", FileSystem.WriteMode.OVERWRITE).setParallelism(1);
 * </pre>
 */
public class OutputResultActor extends AbstractAFluxActor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public OutputResultActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String,String> properties) {
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

        // Get properties of node
        String outputType = this.getProperty(Node07OutputResult.PROPERTY_OUTPUT_TYPE);
        String logIdentifier = this.getProperty(Node07OutputResult.PROPERTY_KAFKA_LOG_IDENTIFIER);

        // Define types that will be used
        TypeName mapInputType = ((ParameterizedTypeName)inputType).typeArguments.get(0); // Double
        TypeName mapOutputType = ClassName.get(String.class); // default for kafka
        if (outputType.equals("csv")) {
            mapOutputType = ParameterizedTypeName.get(API.getClassNameInstance("Tuple1"), mapInputType);
        }

        // Define anonymous class for MapFunction
        MethodSpec.Builder mapMethodSpecBuilder = MethodSpec.methodBuilder(API.getMethodName("MapFunction.map"))
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(mapInputType, "input")
                .addException(Exception.class)
                .returns(mapOutputType);
        if (outputType.equals("kafka")) {
            mapMethodSpecBuilder.addStatement("return $S + $S + $N.toString()",
                    logIdentifier,
                    ": ",
                    "input");
        } else if (outputType.equals("csv")) {
            mapMethodSpecBuilder.addStatement("return $T.$L($N)",
                    API.getClassNameInstance("Tuple1"),
                    API.getMethodName("Tuple1.of"),
                    "input");
        }

        TypeSpec mapFunction = TypeSpec.anonymousClassBuilder("")
                .addSuperinterface(ParameterizedTypeName.get(
                        API.getClassNameInstance("MapFunction"),
                        mapInputType,
                        mapOutputType))
                .addMethod(mapMethodSpecBuilder.build())
                .build();

        // Add code: output
        this.sendOutput("Generating code for: data output");

        Map<String, Object> codeVariables = new LinkedHashMap<>();
        codeVariables.put("inputVar", inputVariableName);
        codeVariables.put("map", API.getMethodName("DataStream.map"));
        codeVariables.put("mapAnonymousClass", mapFunction);
        codeVariables.put("addSink", API.getMethodName("DataStream.addSink"));
        codeVariables.put("kafkaProducer", API.getClassNameInstance("FlinkKafkaProducer08"));
        codeVariables.put("kafkaAddress", this.getProperty(Node07OutputResult.PROPERTY_KAFKA_ADDRESS));
        codeVariables.put("kafkaTopic", this.getProperty(Node07OutputResult.PROPERTY_KAFKA_TOPIC));
        codeVariables.put("simpleStringSchema", API.getClassNameInstance("SimpleStringSchema"));
        codeVariables.put("writeAsCsv", API.getMethodName("DataStream.writeAsCsv"));
        codeVariables.put("csvFilename", this.getProperty(Node07OutputResult.PROPERTY_CSV_FILENAME));
        codeVariables.put("writeMode", API.getClassNameInstance("WriteMode"));
        codeVariables.put("overwrite", API.getMethodName("WriteMode.OVERWRITE"));
        codeVariables.put("setParallelism", API.getMethodName("DataStreamSink.setParallelism"));

        if (outputType.equals("kafka")) {
            code.addNamed(
                    "$inputVar:L" +
                        ".$map:L($mapAnonymousClass:L)" +
                        ".$addSink:L(new $kafkaProducer:T<>(" +
                            "$kafkaAddress:S, $kafkaTopic:S, new $simpleStringSchema:T()));\n",
                    codeVariables);
        } else if (outputType.equals("console")) {
            code.addStatement("$L.$L()",
                    inputVariableName, // result
                    API.getMethodName("DataStream.print")); // print
        } else if (outputType.equals("csv")) {
            code.addNamed(
                    "$inputVar:L" +
                            ".$map:L($mapAnonymousClass:L)" +
                            ".$writeAsCsv:L($csvFilename:S, $writeMode:T.$overwrite:L).$setParallelism:L(1);\n",
                    codeVariables);
        }

        // Output code builder for next actor
        this.setOutput(1, msg);
    }

}