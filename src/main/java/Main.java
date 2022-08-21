import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File("input.txt");
        File output = new File("output.txt");
        String[] inputData;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(input));
        inputData = bufferedReader.readLine().split(",");
        int axisX = Integer.parseInt(inputData[0]);
        int axisY = Integer.parseInt(inputData[1]);
        int cycleNumber = Integer.parseInt(inputData[2]);
        String[][] oldGeneration = new String[axisX][axisY];
        String[][] newGeneration = new String[axisX][axisY];
        int count = 0;
        while (bufferedReader.ready()) {
            String[] rowData = bufferedReader.readLine().split(" ");
            System.arraycopy(rowData, 0, oldGeneration[count], 0, axisX);
            count++;
        }
        System.out.println("======Initial Generation======");
        for (String[] string : oldGeneration) {
            System.out.println(Arrays.toString(string));
        }
        int cycle = 0;
        while (cycle < cycleNumber) {
            cycle +=1;
            for (int x = 0;x < axisX; x++) {
                for (int y = 0; y < axisY; y++) {
                    int neighbours = 0;
                    int col;
                    int row;
                    if (oldGeneration[x][y].equals("X")) {
                        neighbours -= 1;
                    }
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            col = (x + i + axisX) % axisX;
                            row = (y + j + axisY) % axisY;
                            if (oldGeneration[col][row].equals("X")) {
                                neighbours++;
                            }
                        }
                    }
                    if (oldGeneration[x][y].equals("X") && neighbours < 2) {
                        newGeneration[x][y] = "O";
                    } else if (oldGeneration[x][y].equals("X") && neighbours > 3) {
                        newGeneration[x][y] = "O";
                    } else if (oldGeneration[x][y].equals("O") && neighbours == 3) {
                        newGeneration[x][y] = "X";
                    } else
                        newGeneration[x][y] = oldGeneration[x][y];
                }
            }
            System.out.println("========Generation "+cycle+" ========");
            for (String[] string : newGeneration) {
                System.out.println(Arrays.toString(string));
            }
            for (int i = 0; i < axisX; i++) {
                System.arraycopy(newGeneration[i], 0, oldGeneration[i], 0, oldGeneration[i].length);

            }
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output))){
            for(String[] string: newGeneration)
            {
                bufferedWriter.write(Arrays.toString(string) + "\n");
            }
        }catch (IOException e){
            e.getStackTrace();
        }
    }
}
