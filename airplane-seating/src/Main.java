public class Main {

    public static void main(String[] args) {

        AirplaneSeating seating = new AirplaneSeating();

        String occupiedSeats = "1A 2D 3J";
        int rows = 3;

        int countOfFamilies = seating.countOfFamilies(rows, occupiedSeats);
        System.out.print(countOfFamilies);
    }


}
