package Events;

import Connector.Connector;

public interface Event {
    void executeEvent(Connector connector, String executor);
}
