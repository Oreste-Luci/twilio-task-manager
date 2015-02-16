package cl.luci.twilio.dom;

/**
 * Statuses for Tasks
 * @author Oreste Luci
 */
public enum TaskStatusEnum {

    NEW("New"),ACCEPTED("Accepted"),REFUSED("Refused"),UNDETERMINED("Undetermined");

    private final String value;

    private TaskStatusEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
