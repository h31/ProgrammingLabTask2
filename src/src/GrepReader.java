import java.util.Objects;

public class GrepReader {
    private boolean h = false;
    private boolean r = false;
    private boolean i = false;
    private boolean v = false;


    GrepReader(String[] args) {
        for (String arg : args) {
            if (Objects.equals(arg, "-i")) { // игнорирование регистра слов
                i = true;
            }
            if (Objects.equals(arg, "-v")) { // инвертирует условие фильтрации
                v = true;
            }
            if (Objects.equals(arg, "-r")) { // вместо слова задается регулярное выражение для поиска
                r = true;
            }
        }
    }

    boolean getI() {
        return i;
    }

    boolean getV() {
        return v;
    }

    boolean getR() {
        return r;
    }

}
