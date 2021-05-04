import com.thoughtworks.xstream.converters.time.ZonedDateTimeConverter;

import java.time.ZonedDateTime;

public class T2 {
    public static void main(String[] args) {
        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt);
    }
}
