package de.tum.in.aflux.component.flink.actor;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import de.tum.in.aflux.component.flink.Node05TransformationWindow;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.component.flink.util.FlinkFlowMessage;
import de.tum.in.aflux.component.flink.util.JavaCodeGenerator;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This actor is in charge of generating code for a Window transformation in Flink. The output depends on
 * the type of Window (e.g. tumbling, sliding).
 * Example output:
 *
 * <pre>
 *     AllWindowedStream<..., TimeWindow> output = input.windowAll(TumblingEventTimeWindows.of(Time.minutes(1)));
 *     AllWindowedStream<..., TimeWindow> output = input.windowAll(SlidingEventTimeWindows.of(Time.minutes(1),
 *                                                                                            Time.seconds(10)));
 * </pre>
 */
public class TransformationWindowActor extends AbstractAFluxActor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public TransformationWindowActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String, String> properties) {
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
        String windowTypeProperty = this.getProperty(Node05TransformationWindow.PROPERTY_WINDOW_TYPE);
        String windowUnitsProperty = this.getProperty(Node05TransformationWindow.PROPERTY_WINDOW_UNITS);
        String windowSizeProperty = this.getProperty(Node05TransformationWindow.PROPERTY_WINDOW_SIZE);
        String windowSlideProperty = this.getProperty(Node05TransformationWindow.PROPERTY_WINDOW_SLIDE);

        // Define types that will be used
        TypeName windowType = API.getClassNameInstance("TumblingEventTimeWindows");
        String of = API.getMethodName("TumblingEventTimeWindows.of");
        if (windowTypeProperty.equals("Sliding")) {
            windowType = API.getClassNameInstance("SlidingEventTimeWindows");
            of = API.getMethodName("SlidingEventTimeWindows.of");
        }

        TypeName windowTypeParameterized = ParameterizedTypeName.get(
                API.getClassNameInstance("AllWindowedStream"),
                ((ParameterizedTypeName)inputType).typeArguments.get(0),
                API.getClassNameInstance("TimeWindow"));

        // Add code
        this.sendOutput("Generating code for: window transformation");

        String variable1 = JavaCodeGenerator.newVariableName();
        Map<String, Object> codeVariables = new LinkedHashMap<>();
        codeVariables.put("variable1", variable1);
        codeVariables.put("windowTypeParameterized", windowTypeParameterized);
        codeVariables.put("inputDataset", inputVariableName);
        codeVariables.put("windowAll", API.getMethodName("DataStream.windowAll"));
        codeVariables.put("windowType", windowType);
        codeVariables.put("of", of);
        codeVariables.put("timeType", API.getClassNameInstance("Time"));
        codeVariables.put("timeUnit", windowUnitsProperty);
        codeVariables.put("windowSize", windowSizeProperty);
        codeVariables.put("windowSlide", windowSlideProperty);

        if (windowTypeProperty.equals("Tumbling"))
            code.addNamed("$windowTypeParameterized:T $variable1:L = $inputDataset:L.$windowAll:L(" +
                            "$windowType:T.$of:L($timeType:T.$timeUnit:L($windowSize:L)));\n",
                    codeVariables);
        else if (windowTypeProperty.equals("Sliding"))
            code.addNamed("$windowTypeParameterized:T $variable1:L = $inputDataset:L.$windowAll:L(" +
                            "$windowType:T.$of:L($timeType:T.$timeUnit:L($windowSize:L), $timeType:T.$timeUnit:L($windowSlide:L)));\n",
                    codeVariables);

        // Output code builder for next actor
        msg.setCurrentType(windowTypeParameterized);
        msg.setCurrentDataStreamVariableName(variable1);
        this.setOutput(1, msg);
    }
}
