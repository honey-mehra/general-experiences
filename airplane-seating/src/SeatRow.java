/**
 * This class corresponds to a particular row in a plane.
 *
 */
public class SeatRow {

    Block firstBlock;
    Block secondBlock;
    Block thirdBlock;

    public SeatRow() {
        this.firstBlock = new Block(new char[3]);
        this.secondBlock = new Block(new char[4]);
        this.thirdBlock = new Block(new char[3]);
    }

    public Block getFirstBlock() {
        return firstBlock;
    }

    public Block getSecondBlock() {
        return secondBlock;
    }

    public Block getThirdBlock() {
        return thirdBlock;
    }

    /**
     * This method returns the count of families allowed to sit together in a given row.
     * For eg: A B C   D E G H   J K L
     * Row 1   x         x
     * For the above use case the method will return 1 as only in last block we can sit
     * a family of 3 together with each other.
     *
     * @return count of families (of 3) that can be seated in the row together.
     */
    public int familiesAllowedInSeatRow() {
        int count = 0;
        count = firstBlock.canSeatFamilyInThreeBlock() ? count+=1 : count;
        count = secondBlock.canSeatFamilyInFourBlock() ? count+=1 : count;
        count = thirdBlock.canSeatFamilyInThreeBlock() ? count+=1 : count;
        return count;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(char ch : firstBlock.getSeatsInBlock()) {
            builder.append(ch+ " ");
        }
        for(char ch : secondBlock.getSeatsInBlock()) {
            builder.append(ch+ " ");
        }
        for(char ch : thirdBlock.getSeatsInBlock()) {
            builder.append(ch+ " ");
        }
        return builder.toString();
    }
}

/**
 * This class specifies a block in the row. A block can be an array of 3 or 4.
 * The block is initialized when a row is created. The char array can be of
 * size 3 or 4.
 */
class Block {
    char[] block;

    public Block(char[] block) {
        this.block = block;
    }

    public char[] getSeatsInBlock() {
        return this.block;
    }

    /**
     * If in a block of 3:
     * a.) any seat is already taken then we cannot sit a family of three in the block of 3.
     *
     * @return boolean whether we can sit a family of three in this block.
     */
    public boolean canSeatFamilyInThreeBlock() {

        for(int i=0; i<block.length; i++) {
            if(block[i] == 'X') {
                return false;
            }
        }
        return true;
    }

    /**
     * If in a block of 4:
     * a.) Any of non corner seats are occupied then we cannot sit family of three in this block.
     * b.) If non corner seats are empty and any of corner seat is empty then we can sit a family
     *     of three in this block.
     *
     * @return boolean whether we can sit a family of three in this block.
     */
    public boolean canSeatFamilyInFourBlock() {
        if(block[1] == 'X' || block[2] == 'X') {
            return false;
        }
        else {
            if(block[0] != 'X' || block[3] != 'X') {
                return true;
            }
        }
        return false;
    }
}