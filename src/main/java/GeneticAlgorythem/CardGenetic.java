package GeneticAlgorythem;

import Cards.InterfacesGroundclass.Card;
import Repository.CardDatabase;

import java.util.*;

public class CardGenetic {
    private Map<String, Float> cardWeights = new HashMap<>();
    private List<String> order = new ArrayList<>();

    public CardGenetic() {
        List<Card> allCards = CardDatabase.getAllCardsOnce();
        for (Card card : allCards) {
            cardWeights.put(card.getName(), 0f);
        }
        createOrder();
    }

    public CardGenetic(Map<String, Float> genes) {
        this.cardWeights = genes;
        createOrder();
    }

    public CardGenetic getMutation() {
        Map<String, Float> mutatedWeights = new HashMap<>();
        Random r = new Random();
        for (String s : cardWeights.keySet()) {
            int randomness = r.nextInt(-9, 9);
            int mutation = (int) Math.floor(randomness/ 3f);
            mutatedWeights.put(s, cardWeights.get(s) + mutation);
        }
        return new CardGenetic(mutatedWeights);
    }

    public Map<String, Float> getGenes() {
        Map<String, Float> returnMap = new HashMap<>();
        for(String s : cardWeights.keySet()) {
            returnMap.put(s, cardWeights.get(s));
        }
        return returnMap;
    }

    public List<String> getOrder() {
        if (order.isEmpty()) {
            createOrder();
        }
        return order;
    }

    public void printCardgenetics() {
        for (String s : getOrder()) {
            System.out.println(s + ":" + cardWeights.get(s));
        }
    }

    public static CardGenetic getRandomeCardGenetic() {
        Map<String, Float> genes = new HashMap<>();
        Random r = new Random();
        for (String s : CardDatabase.getAllCardsOnce().stream().map(Card::getName).toList()) {
            genes.put(s, r.nextFloat(-5,5));
        }
        return new CardGenetic(genes);
    }

    private void createOrder() {
        List<String> returnOrder = new ArrayList<>();
        Map<String, Float> copiedWeights = getGenes();
        Float max = -999_999f;
        String maxKey = "";
        Float number;
        int size = copiedWeights.size();
        while (size > returnOrder.size()) {
            for (String s : copiedWeights.keySet()) {
                if (returnOrder.contains(s)) continue;
                number = copiedWeights.get(s);
                if (number > max) {
                    maxKey = s;
                    max = number;
                }
            }
            returnOrder.add(maxKey);
            copiedWeights.remove(maxKey);
            max = -999_999f;
        }
        this.order = returnOrder;
    }
}
