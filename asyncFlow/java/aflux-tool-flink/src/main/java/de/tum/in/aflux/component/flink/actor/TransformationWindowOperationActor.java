package de.tum.in.aflux.component.flink.actor;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import de.tum.in.aflux.component.flink.Node06TransformationWindowOperation;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.component.flink.util.FlinkFlowMessage;
import de.tum.in.aflux.component.flink.util.JavaCodeGenerator;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  This actor is in charge of generating code for an Aggregation transformation of a Window dataset.
 *  The output depends on whether the desired aggregation is built-on (e.g. sum, min, max) or custom
 *  (e.g. average).
 *  Example output:
 *
 *  <pre>
 *      DataStream<Double> output = input.max(0);
 *      DataStream<Double> output = input.aggregate(new AverageAggregate());
 *  </pre>
 */
public class TransformationWindowOperationActor extends AbstractAFluxActor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public TransformationWindowOperationActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String, String> properties) {
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
        String operationTypeProperty = this.getProperty(Node06TransformationWindowOperation.PROPERTY_WINDOW_OPERATION_TYPE);
        String aggregateTypeProperty = this.getProperty(Node06TransformationWindowOperation.PROPERTY_WINDOW_AGGREGATE_OPERATION);

        // Define types that will be used
        TypeName outputDataStreamParameterized = ParameterizedTypeName.get(
                API.getClassNameInstance("DataStream"),
                ((ParameterizedTypeName)inputType).typeArguments.get(0));
        TypeName aggregateType = null;
        if (operationTypeProperty.equals("aggregate")) {
            switch(aggregateTypeProperty) {
                case "average": aggregateType = API.getClassNameInstance("AverageAggregate");
                // add more cases here for more custom Aggregate operations defined in the project template
            }
        }

        // Add code
        this.sendOutput("Generating code for: window operation " + operationTypeProperty);

        String variable1 = JavaCodeGenerator.newVariableName();
        Map<String, Object> codeVariables = new LinkedHashMap<>();
        codeVariables.put("variable1", variable1);
        codeVariables.put("previousVariable", inputVariableName);
        codeVariables.put("outputDataStreamParameterized", outputDataStreamParameterized);
        codeVariables.put("operationTypeProperty", operationTypeProperty);
        codeVariables.put("aggregateType", aggregateType);
        codeVariables.put("aggregateTypeArgument", aggregateType != null ? aggregateType : "0");

        code.addNamed("$outputDataStreamParameterized:T $variable1:L = " +
                "$previousVariable:L.$operationTypeProperty:L",
                codeVariables);
        if (operationTypeProperty.equals("aggregate"))
            code.addNamed("(new $aggregateTypeArgument:T());\n", codeVariables);
        else
            code.addNamed("($aggregateTypeArgument:L);\n", codeVariables);

        // Output code builder for next actor
        msg.setCurrentType(outputDataStreamParameterized);
        msg.setCurrentDataStreamVariableName(variable1);
        this.setOutput(1, msg);
    }
}
