package lists;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListCalculator {

    public List<Integer> sum(List<Integer> num1, List<Integer> num2) {
        List<Integer> result = new LinkedList<>();
        Iterator<Integer> iterator1 = num1.iterator();
        Iterator<Integer> iterator2 = num2.iterator();
        int digit1 = 0;
        int digit2 = 0;
        int carrier = 0;
        int sum = 0;
        while (iterator1.hasNext() && iterator2.hasNext()) {
            digit1 = iterator1.next();
            digit2 = iterator2.next();
            sum = digit1 + digit2 + carrier;
            result.add(sum % 10);
            carrier = sum / 10;
        }
        Iterator<Integer> remained = (iterator1.hasNext() ? iterator1 : (iterator2.hasNext() ? iterator2 : null));
        while (remained != null && remained.hasNext()) {
            int last = remained.next();
            sum = last + carrier;
            result.add(sum % 10);
            carrier = sum / 10;
        }
        if (carrier != 0) {
            result.add(carrier);
        }
        return result;
    }

}
