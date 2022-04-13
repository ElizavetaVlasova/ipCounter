package course.ips;

import java.util.BitSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpCounter {
    private final Scanner scanner;

    public IpCounter(Scanner sc) {
        this.scanner = sc;
    }

    public void countIps() {
        Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        int count = 0;
        BitSet lowIndexes = new BitSet();
        BitSet maxIndexes = new BitSet();

        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            Matcher m = pattern.matcher(row);
            if (m.find()) {
                String[] ipAddressInArray = row.split("\\.");
                long result = 0;
                for (int i = 0; i < ipAddressInArray.length; i++) {
                    int power = 3 - i;

                    result += (Integer.parseInt(ipAddressInArray[i]) % 256 * Math.pow(256, power));
                }
                if (result < Integer.MAX_VALUE) {
                    lowIndexes.set((int) result);
                } else {
                    maxIndexes.set((int) (result - Integer.MAX_VALUE));
                }
                count++;
                System.out.println("Process line: " + count);
            }
        }
        System.out.println("There are " + (lowIndexes.cardinality() + maxIndexes.cardinality()) + " unique values");
    }
}
