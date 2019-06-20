package de.tum.in.aflux.component.flink.actor;

import com.squareup.javapoet.*;
import de.tum.in.aflux.component.flink.Node10CepPatternCondition;
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
 * This actor is in charge of generating code for a pattern condition in the
 * Complex Event Processing library (CEP) of Flink. Example output:
 *
 * <pre>
 *      Pattern<TrafficObservation, ?> pattern2 = pattern1
 *          .where(new SimpleCondition<TrafficObservation>() {
 *              {@literal @}Override
 *              public boolean filter(TrafficObservation trafficObservation) throws Exception {
 *                  if (trafficObservation.getCharge() >= 50)
 *                      return true;
 *                  return false;
 *              }
 *          })
 * </pre>
 */
public class CepPatternConditionActor extends AbstractAFluxActor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public CepPatternConditionActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String,String> properties) {
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
        String inputPatternName = msg.getCurrentPatternVariableName();

        // Get properties of node
        String typeOfConditionProperty = this.getProperty(Node10CepPatternCondition.PROPERTY_CONDITION_TYPE);
        String attributeToFilter = this.getProperty(Node10CepPatternCondition.PROPERTY_ATTRIBUTE_TO_FILTER);
        String conditionOperand = this.getProperty(Node10CepPatternCondition.PROPERTY_CONDITION_OPERAND);
        String conditionValue = this.getProperty(Node10CepPatternCondition.PROPERTY_CONDITION_VALUE);

        // Define types that will be used
        TypeName inputTypeArgument = ((ParameterizedTypeName)inputType).typeArguments.get(0);
        TypeName parameterizedPatternType = ParameterizedTypeName.get(
                API.getClassNameInstance("Pattern"),
                inputTypeArgument,
                inputTypeArgument );

        // Define anonymous class for SimpleCondition
        TypeSpec simpleCondition = TypeSpec.anonymousClassBuilder("")
                .addSuperinterface(ParameterizedTypeName.get(
                        API.getClassNameInstance("SimpleCondition"),
                        inputTypeArgument))
                .addMethod(MethodSpec.methodBuilder(API.getMethodName("SimpleCondition.filter"))
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(inputTypeArgument, "value")
                        .addException(Exception.class)
                        .returns(boolean.class)
                        .beginControlFlow("if (value.get$L() $L $L)",
                                attributeToFilter,
                                conditionOperand,
                                conditionValue)
                        .addStatement("return true")
                        .endControlFlow()
                        .addStatement("return false")
                        .build())
                .build();

        // Define anonymous class for MapFunction

        // Add code: output
        this.sendOutput("Generating code for: CEP add condition");

        String variable1 = JavaCodeGenerator.newVariableName();

        Map<String, Object> codeVariables = new LinkedHashMap<>();
        codeVariables.put("oldPattern", inputPatternName);
        codeVariables.put("variable1", variable1);
        codeVariables.put("parameterizedPattern", parameterizedPatternType);
        codeVariables.put("typeOfCondition", typeOfConditionProperty);
        codeVariables.put("simpleCondition", simpleCondition);

        code.addNamed("$parameterizedPattern:T $variable1:L = " +
                        "$oldPattern:L.$typeOfCondition:L($simpleCondition:L);\n",
                codeVariables);

        // Output code builder for next actor
        msg.setCurrentPatternVariableName(variable1);
        this.setOutput(1, msg);
    }

}