import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) {

        int j = 0;

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {

                synchronized (sizeToFreq) {
                    String way = generateRoute("RLRFR", 100);

                    int count = way.length() - way.replace("R", "").length();

                    sizeToFreq.compute(count, (key, oldVal) -> oldVal == null ? 1 : oldVal + 1);
                }
            }).start();
        }

        int max = Collections.max(sizeToFreq.keySet());

        String result = sizeToFreq.entrySet().stream()
                .map(entry -> "- " + entry.getKey() + " (" + entry.getValue() + " раз)")
                .collect(Collectors.joining("\n"));

        System.out.println("Самое частое количество повторений: " + max + " Встретилось " + sizeToFreq.get(max));
        System.out.println(result);

    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}