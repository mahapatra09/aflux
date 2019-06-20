package de.tum.in.aflux.component.flink.actor;

import com.squareup.javapoet.*;
import de.tum.in.aflux.component.flink.Node03TransformationFilter;
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
 * This actor is in charge of generating the code for a Filter transformation in Flink.
 * Example output:
 *
 * <pre>
 *     DataStream<...> output = input.filter(new FilterFunction(...));
 * </pre>
 */
public class TransformationFilterActor extends AbstractAFluxActor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public TransformationFilterActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String, String> properties) {
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
        String location = this.getProperty(Node03TransformationFilter.PROPERTY_FILTERING_LOCATION);
        double filteringLatitude = Double.valueOf(location.split(",")[0]);
        double filteringLongitude = Double.valueOf(location.split(",")[1]);
        double filteringRadius = Double.valueOf(location.split(",")[2]);

        // Define types that will be used
        TypeName mapInputType = ((ParameterizedTypeName)inputType).typeArguments.get(0); // TrafficObservation
        String mapInputTypeSimpleName = mapInputType.toString().split("\\.")
                [mapInputType.toString().split("\\.").length - 1];

        // Define anonymous class for FilterFunction
        Map<String, Object> codeVariables = new LinkedHashMap<>();
        codeVariables.put("refLat", filteringLatitude);
        codeVariables.put("refLng", filteringLongitude);
        codeVariables.put("radius", filteringRadius);
        codeVariables.put("getLat", API.getMethodName(mapInputTypeSimpleName + ".getLatitude"));
        codeVariables.put("getLng", API.getMethodName(mapInputTypeSimpleName + ".getLongitude"));
        TypeSpec filterFunction = TypeSpec.anonymousClassBuilder("")
                .addSuperinterface(ParameterizedTypeName.get(
                        API.getClassNameInstance("FilterFunction"),
                        mapInputType))
                .addMethod(MethodSpec.methodBuilder(API.getMethodName("FilterFunction.filter"))
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(mapInputType, "input")
                        .addException(Exception.class)
                        .returns(boolean.class)
                        .addNamedCode(
                                "final double EARTH_RADIUS = 6371;\n" +
                                "double refLat = $refLat:L;\n" +
                                "double refLng = $refLng:L;\n" +
                                "double radius = $radius:L;\n" +
                                "double currentLat = input.$getLat:L();\n" +
                                "double currentLng = input.$getLng:L();\n" +
                                "double dLat = Math.toRadians(refLat - currentLat);\n" +
                                "double dLng = Math.toRadians(refLng - currentLng);\n" +
                                "double sindLat = Math.sin(dLat / 2);\n" +
                                "double sindLng = Math.sin(dLng / 2);\n" +
                                "double va1 = Math.pow(sindLat, 2) + " +
                                        "Math.pow(sindLng, 2)* Math.cos(Math.toRadians(currentLat)) * Math.cos(Math.toRadians(refLat));\n" +
                                "double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));\n" +
                                "double distance = EARTH_RADIUS * va2;\n" +
                                "return distance < radius;"
                                , codeVariables)
                        .build())
                .build();

        // Add code
        this.sendOutput("Generating code for: filter-by-location transformation");

        String variable1 = JavaCodeGenerator.newVariableName();
        codeVariables.put("inputVar", inputVariableName);
        codeVariables.put("inputType", inputType);
        codeVariables.put("filter", API.getMethodName("DataStream.filter"));
        codeVariables.put("filterAnonymousClass", filterFunction);
        codeVariables.put("variable1", variable1);

        code.addNamed("$inputType:T $variable1:L = $inputVar:L.$filter:L($filterAnonymousClass:L);\n",
                codeVariables);

        // Output code builder for next actor
        msg.setCurrentDataStreamVariableName(variable1);
        this.setOutput(1, msg);
    }
}
