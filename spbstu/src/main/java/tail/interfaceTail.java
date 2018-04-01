package tail;

import java.util.Scanner;

public class interfaceTail {
    public static void main(String[] args) throws Exception {
        String name1 = "";
        String name2 = "";
        String inputCommandString;
        //FileWriter writer = new FileWriter(name1);
        //FileReader reader = new FileReader(name2);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите команду");
        inputCommandString = scanner.nextLine();

        if (inputCommandString.contains("-c") && inputCommandString.contains("-n"))
            throw new IllegalArgumentException("Неверный формат данных");
        String[] command = inputCommandString.split(" ");
        String flag = command[1];

        //writer.close();
        //reader.close();
    }
}
