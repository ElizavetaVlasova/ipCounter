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
        BitSet bitSet = new BitSet();
        Pattern p = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        int count = 0;

        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            Matcher m = p.matcher(row);
            if (m.find()) {
                String[] ipAddressInArray = row.split("\\.");
                long result = 0;
                for (int i = 0; i < ipAddressInArray.length; i++) {
                    int power = 3 - i;

                    result += (Integer.parseInt(ipAddressInArray[i]) % 256 * Math.pow(256, power));
                }
                int index = (int) (result >> 5);
                count++;
                System.out.println("Process line: " + count);
                bitSet.set(index);
            }
        }
        System.out.println("There are " + bitSet.cardinality() + " unique values");
    }
}
