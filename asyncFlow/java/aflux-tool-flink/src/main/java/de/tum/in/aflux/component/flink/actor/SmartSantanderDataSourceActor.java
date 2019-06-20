package de.tum.in.aflux.component.flink.actor;

import com.squareup.javapoet.*;
import de.tum.in.aflux.component.flink.Node02SmartSantanderDataSource;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.component.flink.util.FlinkFlowMessage;
import de.tum.in.aflux.component.flink.util.JavaCodeGenerator;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

import javax.lang.model.element.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This actor is in charge of generating the code to read data from the SmartSantander API.
 * Example output:
 *
 * <pre>
 *     SmartSantanderSource<...> source = new SmartSantanderSource<>(...);
 *     DataStream<...> output = see.addSource(source, TypeInformation.of(...))
 *                                 .assignTimestampsAndWatermarks(...);
 * </pre>
 */
public class SmartSantanderDataSourceActor extends AbstractAFluxActor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public SmartSantanderDataSourceActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String,String> properties) {
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

        // Get properties of node
        String observationNameProperty = this.getProperty(Node02SmartSantanderDataSource.PROPERTY_OBSERVATION_TYPE);
        String updateTimeProperty = this.getProperty(Node02SmartSantanderDataSource.PROPERTY_UPDATE_TIME);

        // Define types that will be used
        TypeName observationType = API.getClassNameInstance(observationNameProperty); // e.g TrafficObservation
        TypeName parameterizedSourceType = ParameterizedTypeName.get(
                API.getClassNameInstance("SmartSantanderSource"),
                observationType); // e.g SmartSantanderSource<TrafficObservation>
        TypeName parameterizedDataStreamType = ParameterizedTypeName.get(
                API.getClassNameInstance("DataStream"),
                observationType); // e.g. DataStream<TrafficObservation>

        TypeName instantType = ClassName.get("java.time", "Instant");
        TypeSpec timestampExtractor = TypeSpec.anonymousClassBuilder("")
                .addSuperinterface(ParameterizedTypeName.get(
                        API.getClassNameInstance("AscendingTimestampExtractor"),
                        observationType))
                .addMethod(MethodSpec.methodBuilder(API.getMethodName("AscendingTimestampExtractor.extractAscendingTimestamp"))
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(observationType, "input")
                        .returns(long.class)
                        .addStatement("return $T.parse($N.$L()).toEpochMilli()",
                                instantType,
                                "input",
                                API.getMethodName(((ClassName) observationType).simpleName() + ".getTimestamp"))
                        .build())
                .build();

        // Add code
        this.sendOutput("Generating code for: SmartSantander data source and stream");

        Map<String, Object> codeVariables = new LinkedHashMap<>();
        codeVariables.put("sourceType", parameterizedSourceType);
        codeVariables.put("variable1", JavaCodeGenerator.newVariableName());
        codeVariables.put("smartSantanderSource", API.getClassNameInstance("SmartSantanderSource"));
        codeVariables.put("observationType", observationType);
        codeVariables.put("smartSantanderAPIEndpoints", API.getClassNameInstance("SmartSantanderAPIEndpoints"));
        codeVariables.put("endpointName", observationNameProperty.split("Observation")[0].toUpperCase());
        codeVariables.put("updateTime", updateTimeProperty);

        codeVariables.put("dataStreamType", parameterizedDataStreamType);
        codeVariables.put("variable2", JavaCodeGenerator.newVariableName());
        codeVariables.put("env", EnvironmentSetUpActor.GENERATED_CODE_VARIABLE_ENV);
        codeVariables.put("addSource", API.getMethodName("StreamExecutionEnvironment.addSource"));
        codeVariables.put("typeInformation", API.getClassNameInstance("TypeInformation"));
        codeVariables.put("of", API.getMethodName("TypeInformation.of"));
        codeVariables.put("assignTimestamps", API.getMethodName("DataStream.assignTimestampsAndWatermarks"));
        codeVariables.put("timestampExtractorAnonymousClass", timestampExtractor);

        code.addNamed( // define data source
                "$sourceType:T $variable1:L = new $smartSantanderSource:T<>(" +
                    "$observationType:T[].class, " +
                    "$smartSantanderAPIEndpoints:T.$endpointName:L, " +
                    "$updateTime:L);\n",
                codeVariables);
        code.addNamed( // define data stream
                "$dataStreamType:T $variable2:L = $env:L.$addSource:L(" +
                        "$variable1:L," +
                        "$typeInformation:T.$of:L($observationType:T.class))" +
                        ".$assignTimestamps:L($timestampExtractorAnonymousClass:L);\n"
                , codeVariables);

        // Output code builder for next actor
        msg.setCurrentType(parameterizedDataStreamType);
        msg.setCurrentDataStreamVariableName((String)(codeVariables.get("variable2")));
        this.setOutput(1, msg);
    }
}
