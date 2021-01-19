import java.util.*;

public class MatrixMult{
    public static void main (String [] Args){
        
        int [] matDimens = getDimens();

        int matOneExpVals = matDimens[0] * matDimens[1];
        int matTwoExpVals = matDimens[2] * matDimens[3];
        int matSize = matOneExpVals + matTwoExpVals;
        int matOneRow = matDimens[0];
        int matOneCol = matDimens[1];
        int matTwoRow = matDimens[2];
        int matTwoCol = matDimens[3];
        int [] matrixOne = new int [matOneExpVals];
        int [] matrixTwo = new int [matTwoExpVals];

        int [] matContents = entMatrix(matOneExpVals, matTwoExpVals);
        int [] products = Multiplier(matOneRow, matOneCol, matTwoRow, matTwoCol, matContents);

        int a = 0;
        for (int i = 0; i < matSize; i++){
            if (i < matOneExpVals){
                matrixOne[i] = matContents [i];
            }

            else {
                matrixTwo[a] = matContents [i];
                a++;
            }
        }

        System.out.println("\n\nMatrix One: ");
        prettyPrint(matrixOne, matOneRow, matOneCol);

        System.out.println("\nMatrix Two: ");
        prettyPrint(matrixTwo, matTwoRow, matTwoCol);


        System.out.println("\n\nThe product of both matrices: ");
        prettyPrint(products, matOneRow, matTwoCol);

        System.out.println();

    }

    // Gets the dimensions of the two matrices and verifies if they are valid/possible
    public static int[] getDimens (){
        int r1 = 0;
        int c1 = -1;
        int r2 = 0;
        int c2 = -1; 
        int [] dimensions = new int [4];

        Scanner in = new Scanner (System.in);

        while (c1 != r2  || (r1 <= 0 || c1 <= 0 || r2 <= 0 || c2 <= 0) ){

            System.out.println("Enter the dimensions of matrix one, separated by whitespace (ex \"3 3\" for 3 rows by 3 columns): ");
            r1 = in.nextInt();
            c1 = in.nextInt();
            System.out.println("Enter the dimensions of matrix two, separated by whitespace(ex \"3 3\" for 3 rows by 3 columns):  ");
            r2 = in.nextInt();
            c2 = in.nextInt();
            
            if ((r1 <= 0 || c1 <= 0 || r2 <= 0 || c2 <= 0)){
                System.out.println("Invalid dimensions entered, they must be greater than 0. Please enter valid dimensions.\n");
                continue;
            }

            else if (c1 != r2){
                System.out.println("The column of matrix one and row of matrix two do not match, therefore this matrix cannot be multiplied. Please enter valid dimensions.\n");
                continue;
            }
        }

        dimensions[0] = r1;
        dimensions [1] = c1;
        dimensions[2] = r2;
        dimensions[3] = c2;

        return dimensions;
    }

    // Handles the entering of numbers into the two matrices
    public static int[] entMatrix (int one, int two){
        int size = one + two;
        int [] contents = new int [size];
        Scanner mat = new Scanner (System.in);
        String [] stringOneInput = new String [0];
        String matOneInput;
        String[] stringTwoInput = new String [0];
        String matTwoInput;

        // Checks if the appropriate amount of numbers are entered for matrix one
        System.out.println("Enter the contents of matrix one, separated by whitespace (From the top leftmost entry to the right, and down each row): ");
        while (stringOneInput.length < one || stringOneInput.length > one){
            matOneInput = mat.nextLine();
            stringOneInput = matOneInput.split(" ");
            if (stringOneInput.length > one){
                System.out.println("Too many numbers entered for a matrix of this size. Please enter your numbers again: ");
            }

            else if (stringOneInput.length < one){
                System.out.println("Not enough numbers entered for a matrix of this size. Please enter your numbers again: ");
            }
        }

        // Checks if the appropriate amount of numbers are entered for matrix two
        System.out.println("Enter the contents of matrix two, separated by whitespace (From the top leftmost entry to the right, and down each row): ");
        while (stringTwoInput.length < two || stringTwoInput.length > two){
            matTwoInput = mat.nextLine();
            stringTwoInput = matTwoInput.split(" ");
            if (stringTwoInput.length > two){
                System.out.println("Too many numbers entered for a matrix of this size. Please enter your numbers again: ");
            }

            else if (stringTwoInput.length < two){
                System.out.println("Not enough numbers entered for a matrix of this size. Please enter your numbers again: ");
            }
        }

        // Puts the numbers entered previously into two different 1D arrays
        int[] matrix = new int[size];
        for (int i = 0; i < size; i++){
            if (i < one){
             contents[i] = Integer.parseInt(stringOneInput[i]);
            }

            else {
                contents[i] = Integer.parseInt(stringTwoInput[i - one]);
            }
        }

        mat.close();
        return contents;
    }

    // Multiplies the two matrices
    public static int[] Multiplier (int matOneX, int matOneY, int matTwoX, int matTwoY, int [] nums){
        int leng = nums.length;
        int [][] matrixOne = new int [matOneX][matOneY];
        int [][] matrixTwo = new int [matTwoX][matTwoY];
        int [] finalMat = new int [matOneX * matTwoY];
        int a = 0;

        // Converts the contents in the 1D arrays into the two 2D matrices
        for (int i = 0; i < matOneX; i++){
            for (int j = 0; j < matOneY; j++){
                matrixOne[i][j] = nums[a];
                a++;
            }
        }


        for (int m = 0; m < matTwoX; m++){
            for (int p = 0; p < matTwoY; p++){
                matrixTwo[m][p] = nums[a];
                a++;
            }
        }

        // Multiplies the contents of these two matrices
        int sum = 0; 
        int fin = 0;
        for (int x = 0; x < matOneX; x++){
            
            for (int y = 0; y < matTwoY; y++){
               
                for (int z = 0; z < matOneY; z++){
                    sum += matrixOne[x][z] * matrixTwo[z][y];
                }
            
            finalMat[fin] = sum;
            sum = 0;
            fin++;

            }
        }

        // Saves the multiplication operation into one 1D array

        return finalMat;
    }

    // Prints out the contents of matrices
    public static void prettyPrint (int [] product, int x, int y){

        int [][] finalProduct = new int [x][y];
        int a = 0;

        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                finalProduct[i][j] = product[a];
                a++;
            }
        }

        for (int s = 0; s < x; s++){
            for (int t = 0; t < y; t++){
                System.out.printf("%6d", finalProduct[s][t]);
            }
            System.out.println();
        }
    }
}
