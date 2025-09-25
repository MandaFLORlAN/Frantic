package Cards.Wishcards;

import Connector.Connector;
import Game.GameState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FantasticFour extends Fantastic {
    public FantasticFour() {
        super();
        super.name = "FantasticFour";
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector, GameState gs) {
        List<String> targets = super.getTargets(executorName, "FantasticFour", connector, this, gs, 4);
        if (targets == null) return;
        Map<String, Integer> attackMap = new HashMap<>();
        for (String target : targets) {
            if (attackMap.containsKey(target)) {
                attackMap.put(target, attackMap.get(target) + 1);
            } else {
                attackMap.put(target, 1);
            }
        }
        for (String target : attackMap.keySet()) {
            connector.makePlayerDraw(target, " Fantastic Four by " + executorName, attackMap.get(target));
        }
        super.executeSpecialFunction(executorName, connector, gs);
    }
}
