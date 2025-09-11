package core;
import classes.*;
import java.time.LocalDate;

import java.util.List;
import java.util.Random;

public class RandomGenerator {
    public static final Random random = new Random();
    public static final String[] colors = {"BLACK", "GREY"};

    public static String randomString(int length) {
        int leftLimit = 97;
        int rightLimit = 122;
        StringBuilder buffer = new StringBuilder(length);

        for(int i = 0; i < length; ++i) {
            int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (float)(rightLimit - leftLimit + 1));
            buffer.append(Character.toChars(randomLimitedInt));
        }

        return buffer.toString();
    }

    public static Courier randomCourierData(){
        return new Courier(
                randomString(8),
                randomString(8),
                randomString(6)
        );
    }
    public static int randomIdOrTrackNumber(){
        return 100000 + random.nextInt(900000);
    }

    public static String randomPhoneNumber(){
        StringBuilder phone = new StringBuilder("+7");
        for(int i = 0; i < 10; i++){
            phone.append(random.nextInt(9));
        }
    return phone.toString();
    }

    public static String[] randomColor(){
        return new String[]{colors[random.nextInt(colors.length)]};
    }

    public static String randomDeliveryDate(){
        LocalDate randomDate = LocalDate.now().plusDays(random.nextInt(90));
        return randomDate.toString();
    }

    public static Order randomOrderData() {
        Random random = new Random();
        int metroStation = random.nextInt(238);
        return new Order(randomString(6),
                randomString(6),
                randomString(12),
                Integer.toString(metroStation),
                randomPhoneNumber(),
                random.nextInt(8),
                randomDeliveryDate(),
                randomString(42),
                randomColor());
    }

    public static MetroStation getRandomStation(List<MetroStation> stations) {
        Random random = new Random();
        int index = random.nextInt(stations.size());
        return stations.get(index);
    }
}
