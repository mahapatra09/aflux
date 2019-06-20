

/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright 2019 Tanmaya Mahapatra
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.tum.in.aflux.flux_engine.impl;

import de.tum.in.aflux.model.*;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.ToolSemanticsCondition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Validates the semantics specified as ToolSemanticsCondition.
 * Assumes elements are connected one-to-one in a linear way, e.g. a--b--c--d, not a--b--c--d
 *                                                                                 \________/
 *
 * @author Federico Fernández
 */
public class FluxJobValidator {

    public static final String ERRORS_TITLE = "SEMANTICS ERRORS: ";

    /**
     * Checks all elements for a given job. Loops over all the activities in the job.
     *
     * @param job the job whose activities will be checked.
     * @return a new job with modified nodes in case errors were found (e.g. different color)
     */
    public static FlowJob validateJob(FlowJob job) {
        // check conditions for each element in each activity
        for (FlowActivity activity : job.getActivities()) {
            // first we create an ordered list of the elements (following order of connectors)
            // non-connected elements are ignored
            List<FlowElement> orderedElements = orderedListOfElements(activity.getConnectors());

            // we check conditions for each element in the flow
            for (int i=0; i<orderedElements.size(); i++) {
                FlowElement element = orderedElements.get(i);
                String elementClassName = element.getType().getClassName();
                Class<?> clazz = null;
                try {
                    clazz = Class.forName(elementClassName);
                    AbstractMainExecutor toolElementInstance =(AbstractMainExecutor) clazz.newInstance();
                    Set<String> result = validateElement(orderedElements, toolElementInstance, i);

                    // if new errors were found, include info and change color and name of node
                    if (result.size() != 0)
                        setErrorUserInfo(element, true, result);
                    else
                        setErrorUserInfo(element, false, null);
                } catch (ClassNotFoundException|IllegalAccessException|InstantiationException e) {
                    e.printStackTrace();
                }
            }
            // include elements with error info in the activity
            replaceElements(activity, orderedElements);
        }

        return job;
    }

    /**
     * Checks all conditions for a given element in the flow it is part of.
     *
     * @param orderedElements a list of elements ordered by the connectors among them.
     * @param toolElement the element itself whose semantics conditions will be checked.
     * @param elementIndex the index of the element in the flow.
     * @return a set of strings containing the conditions that failed, or empty if all passed.
     */
    public static Set<String> validateElement(List<FlowElement> orderedElements, AbstractMainExecutor toolElement, int elementIndex) {
        ToolSemanticsCondition[] conditions = toolElement.getSemanticsConditions();
        Set<String> result = new HashSet<String>();

        if (conditions == null)
            return result;

        // compare all conditions for the given toolElement
        for (ToolSemanticsCondition condition : conditions) {
            Class<? extends AbstractMainExecutor> argumentNode = condition.getConditionNodeClass();
            boolean isPrecedent = condition.isPrecedent();
            boolean isConsecutive = condition.isConsecutive();
            boolean isMandatory = condition.isMandatory();

            boolean argumentNodeWasFound = false;

            // loop over all elements in flow
            for (int i=0; i<orderedElements.size(); i++) {
                String elementClassName = orderedElements.get(i).getType().getClassName();
                Class<?> clazz = null;
                try {
                    clazz = Class.forName(elementClassName);
                    AbstractMainExecutor toolElementInstance =(AbstractMainExecutor) clazz.newInstance();
                    if (!(argumentNode.isInstance(toolElementInstance))) // improve performance for nodes not matching the condition
                        continue;
                    argumentNodeWasFound = true;
                    if (i < elementIndex) { // argumentNode comes before toolElement
                        if (!isPrecedent)
                            result.add(condition.toString());
                        else if (isConsecutive && i < elementIndex - 1)
                            result.add(condition.toString());
                    } else if (i > elementIndex) { // argumentNode comes after toolElement
                        if (isPrecedent)
                            result.add(condition.toString());
                        else if (isConsecutive && i > elementIndex + 1)
                            result.add(condition.toString());
                    }
                } catch (ClassNotFoundException|IllegalAccessException|InstantiationException e) {
                    e.printStackTrace();
                }
            }
            if (isMandatory && !argumentNodeWasFound)
                result.add(condition.toString());
        }
        return result;
    }

    /**
     * Generates a list sorted in terms of the flow order (following the order given by the connectors).
     *
     * @param connectors the list of connectors that compose the flow.
     * @return a sorted list with the elements following the connectors order.
     */
    public static List<FlowElement> orderedListOfElements(List<FlowConnector> connectors) {
        List<FlowElement> orderedFlow = new ArrayList<FlowElement>();

        for (FlowConnector c : connectors){
            FlowElement input = c.getSourceElement();
            FlowElement output = c.getTargetElement();

            boolean containsInput = false;
            boolean containsOutput = false;
            int position = 0;
            for (FlowElement e : orderedFlow) {
                if (e.getId() == input.getId()) {
                    containsInput = true;
                    position = orderedFlow.indexOf(e);
                    break;
                } else if (e.getId() == output.getId()) {
                    containsOutput = true;
                    position = orderedFlow.indexOf(e);
                    break;
                }
            }
            if (containsInput) { // input was already stored as output
                orderedFlow.add(position + 1, output);
            } else if (containsOutput) {
                orderedFlow.add(position, input);
            } else { // add also input
                orderedFlow.add(input);
                orderedFlow.add(output);
            }
        }
        return orderedFlow;
    }

    /**
     * Auxiliar method to search for an element in an activity (compare IDs).
     *
     * @param activity in which the element will be searched for.
     * @param element that is to be found in the activity.
     * @return the index where the element is, or -1 if it was not found.
     */
    public static int findElementIndex(FlowActivity activity, FlowElement element) {
        for (FlowElement e : activity.getElements()) {
            if (e.getId().equals(element.getId()))
                return activity.getElements().indexOf(e);
        }
        return -1;
    }

    /**
     * Changes some properties in an element to show the user that there are (not) errors.
     *
     * @param element the element to edit.
     * @param hasErrors flag indicating whether errors were found or not.
     * @param errorMessages a set of strings indicating the errors, or <pre>null</pre> if no errors were found.
     */
    public static void setErrorUserInfo(FlowElement element, boolean hasErrors, Set<String> errorMessages) {
        // default values for no error
        String color = "#90CAF9";
        String errors = "";
        String nameSuffix = "";

        if (hasErrors) {
            color = "#E84343";
            errors = ERRORS_TITLE + "\n- " + String.join("\n- ", errorMessages);
            nameSuffix = " (*)";
        }

        for (FlowElementProperty property : element.getProperties()) {
            if (property.getName().equals("color"))
                property.setValue(color);
            else if (property.getName().equals("name"))
                property.setValue(property.getValue().split(" \\(\\*\\)")[0] + nameSuffix);
        }
        element.setErrors(errors);
    }

    /**
     * Replaces the elements in an activity by the elements in the argument.
     *
     * @param activity target activity.
     * @param elements target elements.
     */
    public static void replaceElements(FlowActivity activity, List<FlowElement> elements) {
        for (FlowElement e : elements) {
            int index = findElementIndex(activity, e);
            if (index != -1) {
                activity.getElements().set(index, e);
            }
        }
    }

}
