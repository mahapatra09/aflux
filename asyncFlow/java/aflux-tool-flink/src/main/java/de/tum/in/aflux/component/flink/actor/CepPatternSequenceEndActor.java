package de.tum.in.aflux.component.flink.actor;

import com.squareup.javapoet.*;
import de.tum.in.aflux.component.flink.Node11CepPatternEnd;
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
 * This actor is in charge of generating code for the end of the pattern sequence
 * definition. It is in charge of generating alerts (SmartSantanderAlert).
 * Example output:
 *
 * <pre>
 *     PatternStream<...> patternStream = CEP.pattern(dataStream, definedPattern);
 *
 *     DataStream<SmartSantanderAlert> result = patternStream.select(new PatternSelectFunction<..., SmartSantanderAlert>() {
 *         {@literal @}Override
 *         public SmartSantanderAlert select(Map<String, List<...>> map) throws Exception {
 *             ... event = map.get("start").get(0);
 *             return new SmartSantanderAlert("Charge went too high in " + event.toString());
 *         }
 *     });
 * </pre>
 */
public class CepPatternSequenceEndActor extends AbstractAFluxActor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public static final String GENERATED_CODE_VARIABLE_PATTERN_STREAM = "patternStream";

    public CepPatternSequenceEndActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String,String> properties) {
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
        String inputDataStream = msg.getCurrentDataStreamVariableName();
        String inputPatternName = msg.getCurrentPatternVariableName();

        // Get properties of node
        String patternName = this.getProperty(Node11CepPatternEnd.PROPERTY_PATTERN_NAME);
        String elementNumber = this.getProperty(Node11CepPatternEnd.PROPERTY_ELEMENT_NUMBER);
        String alarmMessage = this.getProperty(Node11CepPatternEnd.PROPERTY_ALARM_MESSAGE);

        // Define types that will be used
        TypeName inputTypeArgument = ((ParameterizedTypeName)inputType).typeArguments.get(0);
        TypeName parameterizedPatternStreamType = ParameterizedTypeName.get(
                API.getClassNameInstance("PatternStream"),
                inputTypeArgument);
        TypeName parameterizedDataStreamType = ParameterizedTypeName.get(
                API.getClassNameInstance("DataStream"),
                API.getClassNameInstance("SmartSantanderAlert"));

        // Define anonymous class for PatternSelectFunction
        TypeName parameterMap = ParameterizedTypeName.get(
                ClassName.get("java.util", "Map"),
                ClassName.get("java.lang", "String"),
                ParameterizedTypeName.get(
                        ClassName.get("java.util", "List"),
                        inputTypeArgument));
        TypeSpec patternSelectFunction = TypeSpec.anonymousClassBuilder("")
                .addSuperinterface(ParameterizedTypeName.get(
                        API.getClassNameInstance("PatternSelectFunction"),
                        inputTypeArgument,
                        API.getClassNameInstance("SmartSantanderAlert")))
                .addMethod(MethodSpec.methodBuilder(API.getMethodName("PatternSelectFunction.select"))
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(parameterMap, "map")
                        .addException(Exception.class)
                        .returns(API.getClassNameInstance("SmartSantanderAlert"))
                        .addStatement("$T event = map.get($S).get($L)",
                                inputTypeArgument,
                                patternName,
                                elementNumber)
                        .addStatement("return new $T($S + event.toString())",
                                API.getClassNameInstance("SmartSantanderAlert"),
                                alarmMessage)
                        .build())
                .build();

        // Add code: output
        this.sendOutput("Generating code for: CEP end sequence");

        String variable1 = JavaCodeGenerator.newVariableName();

        Map<String, Object> codeVariables = new LinkedHashMap<>();
        codeVariables.put("patternStreamType", parameterizedPatternStreamType);
        codeVariables.put("patternStream", GENERATED_CODE_VARIABLE_PATTERN_STREAM);
        codeVariables.put("cep", API.getClassNameInstance("CEP"));
        codeVariables.put("pattern", API.getMethodName("CEP.pattern"));
        codeVariables.put("dataStream", inputDataStream);
        codeVariables.put("patternVariable", inputPatternName);
        codeVariables.put("patternSelectFunction", patternSelectFunction);
        codeVariables.put("variable1", variable1);
        codeVariables.put("dataStreamType", parameterizedDataStreamType);
        codeVariables.put("select", API.getMethodName("PatternStream.select"));

        code.addNamed("$patternStreamType:T $patternStream:L = $cep:T.$pattern:L($dataStream:L, $patternVariable:L);\n",
                codeVariables);

        code.addNamed("$dataStreamType:T $variable1:L = $patternStream:L.$select:L($patternSelectFunction:L);\n",
            codeVariables);

        // Output code builder for next actor
        msg.setCurrentDataStreamVariableName(variable1);
        msg.setCurrentType(parameterizedDataStreamType);
        this.setOutput(1, msg);
    }

}