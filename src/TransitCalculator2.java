import java.text.DecimalFormat;
import java.util.Scanner;

class TransitCalculator2 {
    int numberOfDaysUsingTransitSystem;
    int numberOfIndividualRides;

    private final static DecimalFormat df = new DecimalFormat("0.00");

    double totalPricePayPerRide;
    double payPerRide = 2.75;
    double unlimitedRides7Days = 33.00;
    double unlimitedRides30Days = 127.00;


    public TransitCalculator2 (int numberOfDaysUsingTransitSystem, int numberOfIndividualRides) {
        this.numberOfDaysUsingTransitSystem = numberOfDaysUsingTransitSystem;
        this.numberOfIndividualRides = numberOfIndividualRides;
        totalPricePayPerRide = numberOfIndividualRides * payPerRide;
    }

    public FareChoice unlimited7Price() {
        if (numberOfDaysUsingTransitSystem <= 7) {
            if (payPerRide > unlimitedRides7Days / numberOfIndividualRides) {
                return new FareChoice(FareOption.UNLIMITED_7_RIDE, 1);
            }
        } else if (numberOfDaysUsingTransitSystem <= 14) {
            if (payPerRide > (unlimitedRides7Days * 2) / numberOfIndividualRides) {
                return new FareChoice(FareOption.UNLIMITED_7_RIDE, 2);
            }
        } else if (numberOfDaysUsingTransitSystem <= 21) {
            if (payPerRide > (unlimitedRides7Days * 3) / numberOfIndividualRides) {
                return new FareChoice(FareOption.UNLIMITED_7_RIDE, 3);
            }
        } else if (numberOfDaysUsingTransitSystem <= 30) {
            if (payPerRide > (unlimitedRides30Days) / numberOfIndividualRides) {
                return new FareChoice(FareOption.UNLIMITED_30_RIDE, 1);
            }
        } else {
            return new FareChoice(FareOption.MORE_THAN_30_DAYS, 0);
        }
        return new FareChoice(FareOption.PAY_PER_RIDE, numberOfIndividualRides);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransitCalculator2 transitCal = new TransitCalculator2(scanner.nextInt(), scanner.nextInt());

        FareChoice fareChoice = transitCal.unlimited7Price();
        if (fareChoice.fareOption.equals(FareOption.UNLIMITED_7_RIDE)) {
            System.out.println("Buy " + fareChoice.amount + " '7-day Unlimited Rides'-ticket(s) of "+ df.format(transitCal.unlimitedRides7Days));
        } else if (fareChoice.fareOption.equals(FareOption.UNLIMITED_30_RIDE)) {
            System.out.println("Buy " + fareChoice.amount + " '30-day Unlimited Rides'-ticket(s) of "+ df.format(transitCal.unlimitedRides30Days));
        } else if (fareChoice.fareOption.equals(FareOption.MORE_THAN_30_DAYS)) {
            System.out.println("Please select amount of days with a max of 30 days.");
        } else {
            System.out.println("Buy " + fareChoice.amount + " 'Pay-Per-Ride'-ticket(s) of "+ df.format(transitCal.payPerRide) + " per single ride.");
        }

    }
}
