import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(compareLines("Логопед", "Лохопут"));
        System.out.println(getRequisitCodeFromRequirementChar("a 11.4 a2"));
        System.out.println(getRequisitCodeFromRequirementRegex("a 11.4 a2"));

        HashMap<Integer, String> testMap = new HashMap<>();
        testMap.put(12345, "Улица");
        testMap.put(987, "Фонарь");
        testMap.put(23423562, "Аптека");
        System.out.println(getTransparentMap(testMap));
    }

    /*
    Сравнивание строк с нахождением первого отличившегося символа.
    Если строки совпадают, будет возвращена пустая строка.
    Если длина строк разная, то будет выведено сообщение об этом и проверка на совпадение не будет произведена.
    Во всех остальных случаях будет показан первый символ с которого начинается различие
    */
    public static String compareLines(String first, String second) {
        if (first.equals(second))
            return "";

        if (first.length() != second.length()) {
            return "Разная длина строк \n" +
                    "Длина строки №1: " + first.length() + "\n" +
                    "Длина строки №2: " + second.length();
        }

        String out = "";
        int maxVisibleStr = 15;

        for (int i = 0; i < first.length(); i++) {
            String str = i < first.length() - 1 ? first.substring(i, i + 1) : first.substring(i);
            String str1 = i < first.length() - 1 ? second.substring(i, i + 1) : second.substring(i);
            if (!str.equals(str1)) {
                out += "Разница в элементе номер " + i + ", в первой строке символ '" + str + "', во второй строке '" + str1 + "'\n";
                out += i > maxVisibleStr ? "..." + first.substring(i - maxVisibleStr, Math.min(i + maxVisibleStr, first.length())) + "...\n" : "..." + first + "...\n";

                int repeat = 2 + Math.min(i, maxVisibleStr);
                out += new String(new char[repeat]).replace("", " ");
                out += "^\n";
                //таким образом заканчиваем после нахождения первого различия, если убрать break, то будут отображены все различия
                break;
            }
        }
        return out;
    }

    /*
    Поиск и вывод первого найденного числа (целого или дробного)
    с посимвольным разбором
     */
    public static String getRequisitCodeFromRequirementChar(String title) {
        String out = "";
        char chars [] = title.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(Character.isDigit(chars[i])) {
                out += chars[i];
                continue;
            }
            if( i > 0 && Character.isDigit(chars[i-1])){
                if(chars[i] == '.') {
                    out += chars[i];
                    continue;
                }
                if( !Character.isDigit(chars[i])){
                    break;
                }
            }
        }
        return out;
    }
    /*
    Поиск и вывод первого найденного числа (целого или дробного)
    с помощью регулярных выражений
    */
    public static String getRequisitCodeFromRequirementRegex(String title) {
        String regex = "\\d\\.\\d+|\\d+\\.\\d*|\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(title);
        while (matcher.find()){
            return title.substring(matcher.start(), matcher.end());
        }
        return "";
    }

    //Создаётся новая перевёрнутая мапа, т.е. ключ значения поменялись местами
    public static Map<?, ?> getTransparentMap(Map inMap){
        Map outMap = new HashMap();
        Set<Map.Entry<?,?>> entrySet = inMap.entrySet();

        for (Map.Entry tempSet: entrySet) {
            outMap.put(tempSet.getValue(), tempSet.getKey());
        }
        return outMap;
    }
}
