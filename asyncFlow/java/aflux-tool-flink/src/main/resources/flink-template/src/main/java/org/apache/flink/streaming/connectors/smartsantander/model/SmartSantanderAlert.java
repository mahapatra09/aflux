package org.apache.flink.streaming.connectors.smartsantander.model;

import java.util.Objects;

public class SmartSantanderAlert {

    private String message;

    public SmartSantanderAlert(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Alert [" + message + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SmartSantanderAlert)) {
            return false;
        }
        SmartSantanderAlert other = (SmartSantanderAlert) o;
        return Objects.equals(this.getMessage(), other.getMessage());
    }

}
