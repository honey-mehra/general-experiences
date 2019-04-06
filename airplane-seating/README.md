Problem Statement: Count number of families (in a group of 3) that can be seated together

The plane seating is in following format:

1. There are 'N' rows in planes
2. Each row has seats in block of three, block of four and another block of three

Eg      A B C    D E G H   J K L
     
Row 1       X        X

Row 2

Row 3            X                

We are given number of rows in a plane as an int variable.
And the seats that are already occupied as a String separated by space characters:
For eg: occupiedSeats : "1C 1G 3D" specifies
 a.) In row 1 seats C and G are already taken.
 b.) In row 3 seat D is already taken.

int countOfFamilies(int rows, String occupiedSeats)

Write a program that takes above two inputs as arguments and returns the count of families
(in a group of 3) that can be sit together.
