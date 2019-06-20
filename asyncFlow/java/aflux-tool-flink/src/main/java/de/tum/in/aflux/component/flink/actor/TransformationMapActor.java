package de.tum.in.aflux.component.flink.actor;

import com.squareup.javapoet.*;
import de.tum.in.aflux.component.flink.Node04TransformationMap;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.component.flink.util.FlinkFlowMessage;
import de.tum.in.aflux.component.flink.util.JavaCodeGenerator;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

import javax.lang.model.element.Modifier;
import java.util.Map;

/**
 * This actor is in charge of generating code for a Map transformation in Flink.
 * Example output:
 *
 * <pre>
 *     DataStream<...> output = input.map(new MapFunction(...));
 * </pre>
 */
public class TransformationMapActor extends AbstractAFluxActor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public TransformationMapActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String, String> properties) {
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
        String attributeToMap = this.getProperty(Node04TransformationMap.PROPERTY_ATTRIBUTE_TO_MAP);
        attributeToMap = attributeToMap.substring(0,1).toUpperCase() + attributeToMap.substring(1);

        // Define types that will be used
        TypeName mapInputType = ((ParameterizedTypeName)inputType).typeArguments.get(0); // TrafficObservation
        TypeName mapOutputType = ClassName.get(Double.class);
        TypeName outputDataStreamType = ParameterizedTypeName.get(
            API.getClassNameInstance("DataStream"),
                mapOutputType); // <DataStream<Double>

        // Define anonymous class for MapFunction
        TypeSpec mapFunction = TypeSpec.anonymousClassBuilder("")
            .addSuperinterface(ParameterizedTypeName.get(
                API.getClassNameInstance("MapFunction"),
                mapInputType,
                    mapOutputType))
            .addMethod(MethodSpec.methodBuilder(API.getMethodName("MapFunction.map"))
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(mapInputType, "value")
                .addException(Exception.class)
                .returns(mapOutputType)
                .addStatement("return (double)$N.get$L()",
                    "value",
                    attributeToMap)
                .build())
            .build();

        // Add code
        this.sendOutput("Generating code for: map transformation");

        String variable1 = JavaCodeGenerator.newVariableName();
        code.addStatement("$T $L = $L.$L($L)",
            outputDataStreamType, // DataStream<Double>
            variable1, // result
            inputVariableName, // observations
            API.getMethodName("DataStream.map"), // map
            mapFunction); // anonymous MapFunction class

        // Output code builder for next actor
        msg.setCurrentType(outputDataStreamType);
        msg.setCurrentDataStreamVariableName(variable1);
        this.setOutput(1, msg);
    }
}
