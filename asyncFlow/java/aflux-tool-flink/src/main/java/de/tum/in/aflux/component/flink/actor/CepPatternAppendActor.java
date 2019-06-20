package de.tum.in.aflux.component.flink.actor;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import de.tum.in.aflux.component.flink.Node09CepPatternAppend;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.component.flink.util.FlinkFlowMessage;
import de.tum.in.aflux.component.flink.util.JavaCodeGenerator;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This actor is in charge of generating code for beginning the definition of a
 * pattern sequence in the Complex Event Processing library (CEP) of Flink.
 * Example output:
 *
 * <pre>
 *     Pattern<..., ?> pattern1 = Pattern.<...>begin("start", skipStrategy);
 *     Pattern<..., ?> pattern2 = oldPattern.next("middle", skipStrategy);
 * </pre>
 *
 * It is also possible to add quantifiers, such as:
 *
 * <pre>
 *      Pattern<..., ?> pattern2 = oldPattern.next("middle", skipStrategy).oneOrMore();
 * </pre>
 */
public class CepPatternAppendActor extends AbstractAFluxActor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public CepPatternAppendActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String,String> properties) {
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
        String oldPattern = msg.getCurrentPatternVariableName();

        // Get properties of node
        String newPatternName = this.getProperty(Node09CepPatternAppend.PROPERTY_PATTERN_NAME);
        String contiguityFunction = this.getProperty(Node09CepPatternAppend.PROPERTY_PATTERN_CONTIGUITY);
        int minRepetitionsProperty = Integer.valueOf(this.getProperty(Node09CepPatternAppend.PROPERTY_PATTERN_REPEAT_MIN));
        int maxRepetitionsProperty = Integer.valueOf(this.getProperty(Node09CepPatternAppend.PROPERTY_PATTERN_REPEAT_MAX));
        boolean optionalPattern = Boolean.valueOf(this.getProperty(Node09CepPatternAppend.PROPERTY_PATTERN_QUANTIFIER_OPTIONAL));
        boolean greedyPattern = Boolean.valueOf(this.getProperty(Node09CepPatternAppend.PROPERTY_PATTERN_QUANTIFIER_GREEDY));
        boolean consecutivePattern = Boolean.valueOf(this.getProperty(Node09CepPatternAppend.PROPERTY_PATTERN_QUANTIFIER_CONSECUTIVE));
        int withinTime = Integer.valueOf(this.getProperty(Node09CepPatternAppend.PROPERTY_PATTERN_WITHIN));

        // Define types that will be used
        TypeName inputTypeArgument = ((ParameterizedTypeName)inputType).typeArguments.get(0);
        TypeName parameterizedPatternType = ParameterizedTypeName.get(
                API.getClassNameInstance("Pattern"),
                inputTypeArgument,
                inputTypeArgument );

        // Add code: output
        this.sendOutput("Generating code for: CEP add pattern");

        String variable1 = JavaCodeGenerator.newVariableName();

        Map<String, Object> codeVariables = new LinkedHashMap<>();
        codeVariables.put("inputType", inputTypeArgument);
        codeVariables.put("variable1", variable1);
        codeVariables.put("parameterizedPattern", parameterizedPatternType);
        codeVariables.put("pattern", API.getClassNameInstance("Pattern"));
        codeVariables.put("patternName", newPatternName);
        codeVariables.put("oldPattern", oldPattern);
        codeVariables.put("contiguityFunction", contiguityFunction);
        codeVariables.put("begin", API.getMethodName("Pattern.begin"));
        codeVariables.put("strategy", CepPatternSequenceBeginActor.GENERATED_CODE_VARIABLE_STRATEGY);
        codeVariables.put("timesOrMore", API.getMethodName("Pattern.timesOrMore"));
        codeVariables.put("times", API.getMethodName("Pattern.times"));
        codeVariables.put("optional", API.getMethodName("Pattern.optional"));
        codeVariables.put("greedy", API.getMethodName("Pattern.greedy"));
        codeVariables.put("consecutive", API.getMethodName("Pattern.consecutive"));
        codeVariables.put("min", minRepetitionsProperty);
        codeVariables.put("max", maxRepetitionsProperty);
        codeVariables.put("withinTime", withinTime);
        codeVariables.put("within", API.getMethodName("Pattern.within"));
        codeVariables.put("timeType", API.getClassNameInstance("Time"));

        // new pattern or append pattern
        if (oldPattern == null)
            code.addNamed("$parameterizedPattern:T $variable1:L = " +
                            "$pattern:T.<$inputType:T>$begin:L($patternName:S, strategy)",
                    codeVariables);
        else
            code.addNamed("$parameterizedPattern:T $variable1:L = " +
                            "$oldPattern:L.$contiguityFunction:L($patternName:S)",
                    codeVariables);

        // quantifiers
        if (minRepetitionsProperty == maxRepetitionsProperty) {
            code.addNamed(".$times:L($min:L)", codeVariables);
        } else {
            if (maxRepetitionsProperty == -1) {
                code.addNamed(".$timesOrMore:L($min:L)", codeVariables);
            } else {
                code.addNamed(".$times:L($min:L, $max:L)", codeVariables);
            }
        }
        if (optionalPattern)
            code.addNamed(".$optional:L()", codeVariables);
        if (greedyPattern)
            code.addNamed(".$greedy:L()", codeVariables);
        if (consecutivePattern)
            code.addNamed(".$consecutive:L()", codeVariables);
        if (withinTime != -1)
            code.addNamed(".$within:L($timeType:T.seconds($withinTime:L))", codeVariables);

        code.add(";\n");

        // Output code builder for next actor
        msg.setCurrentPatternVariableName(variable1);
        this.setOutput(1, msg);
    }

}