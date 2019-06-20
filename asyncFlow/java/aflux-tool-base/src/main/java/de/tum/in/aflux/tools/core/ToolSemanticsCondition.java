

/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright [2019] [Tanmaya Mahapatra]
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

package de.tum.in.aflux.tools.core;

/**
 * This class is used to enforce semantics among tools when creating a flow.
 * It allows to define conditions like:
 *
 * <pre>
 *     ToolA should/must come (immediately) before/after ToolB.
 * </pre>
 *
 * Where:
 * <ul>
 *     <li><pre>ToolA</pre> is the tool in which the ToolSemanticsCondition is defined.</li>
 *     <li><pre>should/must</pre> depends on the <pre>isMandatory</pre> flag.</li>
 *     <li><pre>immediately</pre> depends on the <pre>isConsecutive</pre> flag.</li>
 *     <li><pre>before/after</pre> depends on the <pre>isPrecedent</pre> flag.</li>
 *     <li><pre>ToolB</pre> is the tool defined in the <pre>conditionNodeClass</pre> attribute.</li>
 * </ul>
 *
 * @author Federico Fernández
 */
public class ToolSemanticsCondition {

    /**
     * The tool specified at the end of the condition sentence (ToolB):
     *
     * <pre>
     *      ToolA should/must come (immediately) before/after ToolB.
     * </pre>
     */
    private Class<? extends AbstractMainExecutor> conditionNodeClass;

    /**
     * Flag to indicate if the <pre>conditionNodeClass</pre> should come before or after. TIP: think of whether or not
     * the tool indicated in <pre>conditionNodeClass</pre> is precedent.
     *
     * <ul>
     *     <li><pre>isPrecedent</pre> set to <pre>true</pre>: condition is of precedence, e.g. "ToolA (...) AFTER ToolB".</li>
     *     <li><pre>isPrecedent</pre> set to <pre>false</pre>: condition is NOT of precedence, e.g. "ToolA (...) BEFORE ToolB".</li>
     * </ul>
     */
    private boolean isPrecedent;

    /**
     * Flag to indicate if the contiguity defined in the condition is strict. This is to say:
     *
     * <ul>
     *     <li><pre>isConsecutive</pre> set to <pre>true</pre>: contiguity is strict, e.g. "ToolA (...) IMMEDIATELY (...) ToolB".</li>
     *     <li><pre>isConsecutive</pre> set to <pre>false</pre>: contiguity is relaxed, e.g. "ToolA (...) ToolB".</li>
     * </ul>
     */
    private boolean isConsecutive;

    /**
     * Flag to indicate if the condition is mandatory. This is to say:
     *
     * <ul>
     *     <li><pre>isMandatory</pre> set to <pre>true</pre>: condition must be met, e.g. "ToolA MUST (...) ToolB".</li>
     *     <li><pre>isMandatory</pre> set to <pre>false</pre>: condition should be met, but there is no obligation, e.g. "ToolA SHOULD (...) ToolB".
     *     In other words, if ToolB exists, then it should come (immediately) after/before ToolA. The condition is also met if
     *     ToolB doesn't exist.</li>
     * </ul>
     */
    private boolean isMandatory;

    public ToolSemanticsCondition(Class<? extends AbstractMainExecutor> conditionNodeClass, boolean isPrecedent, boolean isConsecutive, boolean isMandatory) {
        this.conditionNodeClass = conditionNodeClass;
        this.isPrecedent = isPrecedent;
        this.isConsecutive = isConsecutive;
        this.isMandatory = isMandatory;
    }

    public Class<? extends AbstractMainExecutor> getConditionNodeClass() {
        return conditionNodeClass;
    }

    public void setConditionNodeClass(Class<? extends AbstractMainExecutor> conditionNodeClass) {
        this.conditionNodeClass = conditionNodeClass;
    }

    public boolean isPrecedent() {
        return isPrecedent;
    }

    public void setPrecedent(boolean precedent) {
        isPrecedent = precedent;
    }

    public boolean isConsecutive() {
        return isConsecutive;
    }

    public void setConsecutive(boolean consecutive) {
        isConsecutive = consecutive;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    @Override
    public String toString() {
        String precedenceMessage = this.isPrecedent ? "after " : "before ";
        String consecutiveMessage = this.isConsecutive ? "immediately " : "";
        String mandatoryMessage = this.isMandatory ? "must " : "should ";
        String nodeName = null;
        try {
            nodeName = (String)this.getConditionNodeClass().getField("NAME").get(null);
        } catch (NoSuchFieldException e) {
            nodeName = this.getConditionNodeClass().getSimpleName();
        } catch (IllegalAccessException e) {
            nodeName = this.getConditionNodeClass().getSimpleName();
        }
        return "This node " + mandatoryMessage + "come " + consecutiveMessage + precedenceMessage + nodeName;
    }
}