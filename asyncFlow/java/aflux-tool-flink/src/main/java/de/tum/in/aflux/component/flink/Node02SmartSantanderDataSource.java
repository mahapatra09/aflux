package de.tum.in.aflux.component.flink;

import com.squareup.javapoet.ClassName;
import de.tum.in.aflux.component.flink.actor.SmartSantanderDataSourceActor;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.tools.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Node02SmartSantanderDataSource extends AbstractMainExecutor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public static final String NAME = "SmartSntndr Data";
    public static final String PROPERTY_OBSERVATION_TYPE = "Observation Type";
    public static final String PROPERTY_UPDATE_TIME = "Update Time";

    public static final ToolSemanticsCondition[] semanticsConditions = {
            new ToolSemanticsCondition(
                    Node01EnvironmentSetUp.class,
                    true,
                    true,
                    true)
    };

    public static ToolProperty updateTimeProperty = new ToolProperty(
            PROPERTY_UPDATE_TIME,
            "4",
            PropertyInputType.TEXT,
            null,
            "Frequency of data updates expressed in seconds",
            "",
            false);

    public Node02SmartSantanderDataSource() {
        super(Node02SmartSantanderDataSource.NAME,
                SmartSantanderDataSourceActor.class.getCanonicalName(),
                Node02SmartSantanderDataSource.class.getName(),
                1,
                1,
                NodeLaunchType.LAUNCHED_BY_DATA,
                false,
                null,
                semanticsConditions);

        // dynamically generate names from SmartSantander Model
        List<ClassName> observationClasses = API.getChildClasses("SmartSantanderObservation");
        List<ToolPropertyOption> observationClassesOptions = new ArrayList<>();
        for (ClassName c : observationClasses) {
            // fancy name removes "Observation" and separates words
            String fancyName = String.join(
                    " ",
                    c.simpleName().split("Observation")[0].split("(?=\\p{Upper})"));
            observationClassesOptions.add(new ToolPropertyOption(fancyName, c.simpleName()));
        }

        ToolPropertyOption[] options = new ToolPropertyOption[observationClassesOptions.size()];
        ToolProperty observationTypeProperty = new ToolProperty(
                PROPERTY_OBSERVATION_TYPE,
                "TrafficObservation",
                PropertyInputType.SELECT,
                observationClassesOptions.toArray(options),
                "Choose the type of observation from SmartSantander API that you wish to use",
                "",
                false);

        ToolProperty[] properties = this.getProperties();
        properties = Stream.of(properties, new ToolProperty[]{updateTimeProperty, observationTypeProperty})
                .flatMap(Stream::of)
                .toArray(ToolProperty[]::new);
        this.setProperties(properties);
        this.setColor("#e65270");
    }

}
