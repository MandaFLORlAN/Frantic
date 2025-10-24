package Repository;

public class FranticConfigs {
    public static int NUMBER_OF_START_CARDS = 9;

    public static int ratio(String cardType) {
        return switch (cardType) {
            case "RegularCard" -> 2;
            case "BlackCard" -> 1;
            case "Fantastic" -> 12;
            case "FantasticFour" -> 6;
            case "Equality" -> 4;
            case "Gift" -> 2;
            case "Exchange" -> 1;
            case "SecondChance" -> 2;
            case "Skip" -> 2;
            case "TROUBLEMAKER" -> 2;
            case "Counterattack" -> 5;
            case "FuckYou" -> 1;
            case "NiceTry" -> 11;
            default -> 0;
        };
    }
}
