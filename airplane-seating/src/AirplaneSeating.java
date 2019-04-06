import java.util.HashMap;
import java.util.Map;

public class AirplaneSeating {

    /**
     * This method returns the count of families (with 3 people) that can be seated together in plane
     * which has some seats already occupied.
     *
     * Eg input:
     * N : 3
     * occupiedSeats : "1C 1G 3D"
     *
     * @param N specifies number of rows in plane.
     * @param occupiedSeats specifies what all seats have already been occupied in the plane
     * @return
     */
    public int countOfFamilies(int N, String occupiedSeats) {
        int count = 0;

        Map<Integer, SeatRow> occupiedSeating = new HashMap<>();

        String[] tokens = occupiedSeats.split(" ");
        for(String token : tokens) {

            if(token.isEmpty()) {
                continue;
            }

            Integer rowNumber = Integer.parseInt(token.substring(0,1));
            Character occupiedSeatInRowNumber =  token.toCharArray()[1];

            if(occupiedSeating.get(rowNumber) != null) {
                SeatRow row = occupiedSeating.get(rowNumber);
                markOccupiedSeatsInRow(row, occupiedSeatInRowNumber);
            }
            else {
                SeatRow row = new SeatRow();
                markOccupiedSeatsInRow(row, occupiedSeatInRowNumber);
                occupiedSeating.put(rowNumber, row);
            }
        }

        for(int row=1; row<=N; row++) {
            if(occupiedSeating.get(row) != null) {
                SeatRow seatRow = occupiedSeating.get(row);
                count += seatRow.familiesAllowedInSeatRow();
            }
            else {
                count += 3;
            }
        }
        return count;
    }

    /**
     * This method marks the seats which are already taken in a row with 'X'.
     *
     * @param row
     * @param occupiedSeatInRowNumber
     */
    public static void markOccupiedSeatsInRow(SeatRow row, Character occupiedSeatInRowNumber) {

        switch (occupiedSeatInRowNumber) {

            case 'A':
            case 'B':
            case 'C': {
                Block block = row.getFirstBlock();
                char[] seats = block.getSeatsInBlock();
                seats[occupiedSeatInRowNumber - 'A'] = 'X';
                break;
            }
            case 'J':
            case 'K':
            case 'L': {
                Block block = row.getThirdBlock();
                char[] seats = block.getSeatsInBlock();
                seats[occupiedSeatInRowNumber - 'J'] = 'X';
                break;
            }
            case 'D':
            case 'E': {
                Block block = row.getSecondBlock();
                char[] seats = block.getSeatsInBlock();
                seats[occupiedSeatInRowNumber - 'D'] = 'X';
                break;
            }
            // As 'D E G H' is the block and F is missing so we have to fill block position by adding 2 to the array sub position.
            case 'G':
            case 'H': {
                Block block = row.getSecondBlock();
                char[] seats = block.getSeatsInBlock();
                seats[occupiedSeatInRowNumber - 'G'+2] = 'X';
                break;
            }
            default:
                break;
        }
    }
}