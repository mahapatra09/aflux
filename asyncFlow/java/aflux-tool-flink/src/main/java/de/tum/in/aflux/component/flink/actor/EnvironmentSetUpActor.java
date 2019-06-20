package de.tum.in.aflux.component.flink.actor;

import com.squareup.javapoet.CodeBlock;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.component.flink.util.FlinkFlowMessage;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

import java.util.Map;

/**
 * This actor is in charge of generating the code to set-up Flink and its environment.
 * Example output:
 *
 * <pre>
 *     StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
 *     env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
 * </pre>
 */
public class EnvironmentSetUpActor extends AbstractAFluxActor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public static final String GENERATED_CODE_VARIABLE_ENV = "env";

    public EnvironmentSetUpActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String,String> properties) {
        super(fluxId, fluxEnvironment, fluxRunner, properties, -1);
    }

    @Override
    protected void runCore(Object message) throws Exception {
        CodeBlock.Builder code = CodeBlock.builder();

        this.sendOutput("Generating code for: environment set-up");

        code.addStatement("$T $L = $T.$L()",
                API.getClassNameInstance("StreamExecutionEnvironment"),
                GENERATED_CODE_VARIABLE_ENV,
                API.getClassNameInstance("StreamExecutionEnvironment"),
                API.getMethodName("StreamExecutionEnvironment.getExecutionEnvironment"));

        code.addStatement("$N.$L($T.$L)",
                GENERATED_CODE_VARIABLE_ENV,
                API.getMethodName("StreamExecutionEnvironment.setStreamTimeCharacteristic"),
                API.getClassNameInstance("TimeCharacteristic"),
                API.getMethodName("TimeCharacteristic.EventTime"));

        // output code builder for next actor
        this.setOutput(1, new FlinkFlowMessage(code));
    }

}