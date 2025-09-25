package Events;

public abstract class BaseEvent implements Event{
    public BaseEvent() {
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
