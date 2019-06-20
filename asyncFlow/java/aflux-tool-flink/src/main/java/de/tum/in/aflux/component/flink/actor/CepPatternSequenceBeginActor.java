package de.tum.in.aflux.component.flink.actor;

import com.squareup.javapoet.CodeBlock;
import de.tum.in.aflux.component.flink.Node08CepPatternBegin;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.component.flink.util.FlinkFlowMessage;
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
 *     AfterMatchSkipStrategy skipStrategy = AfterMatchSkipStrategy.noSkip();
 * </pre>
 */
public class CepPatternSequenceBeginActor extends AbstractAFluxActor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public static final String GENERATED_CODE_VARIABLE_STRATEGY = "strategy";

    public CepPatternSequenceBeginActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String,String> properties) {
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
        String skipStrategy = this.getProperty(Node08CepPatternBegin.PROPERTY_SKIP_STRATEGY);

        // Add code: output
        this.sendOutput("Generating code for: CEP begin pattern sequence");

        Map<String, Object> codeVariables = new LinkedHashMap<>();
        codeVariables.put("strategy", GENERATED_CODE_VARIABLE_STRATEGY);
        codeVariables.put("skipStrategy", API.getClassNameInstance("AfterMatchSkipStrategy"));
        codeVariables.put("chosenStrategy", skipStrategy);

        code.addNamed("$skipStrategy:T $strategy:L = $skipStrategy:T.$chosenStrategy:L();\n",
                codeVariables);

        // Output code builder for next actor
        this.setOutput(1, msg);
    }

}