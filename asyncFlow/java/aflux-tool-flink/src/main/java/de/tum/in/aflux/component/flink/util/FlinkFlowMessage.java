package de.tum.in.aflux.component.flink.util;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;

public class FlinkFlowMessage {

    private CodeBlock.Builder code;
    private TypeName currentType;
    private String currentDataStreamVariableName;
    private String currentPatternVariableName;

    public FlinkFlowMessage(CodeBlock.Builder code) {
        this.code = code;
        this.currentType = null;
        this.currentDataStreamVariableName = null;
    }

    public String getCurrentDataStreamVariableName() {
        return currentDataStreamVariableName;
    }

    public void setCurrentDataStreamVariableName(String currentDataStreamVariableName) {
        this.currentDataStreamVariableName = currentDataStreamVariableName;
    }

    public String getCurrentPatternVariableName() {
        return currentPatternVariableName;
    }

    public void setCurrentPatternVariableName(String currentPatternVariableName) {
        this.currentPatternVariableName = currentPatternVariableName;
    }

    public CodeBlock.Builder getCode() {
        return code;
    }

    public void setCode(CodeBlock.Builder code) {
        this.code = code;
    }

    public TypeName getCurrentType() {
        return currentType;
    }

    public void setCurrentType(TypeName currentType) {
        this.currentType = currentType;
    }

    public static FlinkFlowMessage fromRawMessage(Object message) throws IllegalStateException {
        if ((message != null) && (message instanceof FlinkFlowMessage)) {
            return (FlinkFlowMessage) message;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
