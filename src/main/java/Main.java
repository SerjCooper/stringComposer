
public class Main {

    public static void main(String[] args) {
        System.out.println(compareLines("Логопед", "Лохопут"));
        System.out.println(compareLines("Логопед", "Логопед"));
        System.out.println(compareLines("Как же много букв в этом огромном тексте, это просто что-то. Олололо", "Как же много букФ в этом огромном тексте, это просто что-то. Олололо"));
        System.out.println(compareLines("Разная длина строка", "Вот"));
        System.out.println(compareLines("vze", ""));
    }

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
}
