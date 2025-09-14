package Repository;

public class FranticConfigs {
    public static int NUMBER_OF_START_CARDS = 9;

    public static int ratio(String cardType) {
        return switch (cardType) {
            case "RegularCard" -> 2;
            case "BlackCard" -> 1;
            case "Fantastic" -> 12;
            default -> 0;
        };
    }
}
