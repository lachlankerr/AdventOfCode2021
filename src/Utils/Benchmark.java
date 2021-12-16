package Utils;

import java.util.function.Supplier;

public class Benchmark {
    public static void Run(Supplier<Object> func) {
        var startTime = System.currentTimeMillis();
        var result = func.get();
        var endTime = System.currentTimeMillis();
        var difference = endTime - startTime;

        var secondsInMilli = 1000l;
        var minutesInMilli = secondsInMilli * 60l;
        var hoursInMilli = minutesInMilli * 60l;
        //var daysInMilli = hoursInMilli * 24l;

        //long elapsedDays = different / daysInMilli;
        //different = different % daysInMilli;

        var elapsedHours = difference / hoursInMilli;
        difference = difference % hoursInMilli;

        var elapsedMinutes = difference / minutesInMilli;
        difference = difference % minutesInMilli;

        var elapsedSeconds = difference / secondsInMilli;
        difference = difference % secondsInMilli;

        var elapsedMilliseconds = difference;

        var duration = "";
        if (elapsedHours > 0) {
            duration = elapsedHours + ":" + String.format("%02d", elapsedMinutes) + ":" + String.format("%02d", elapsedSeconds) + "." + elapsedMilliseconds;
        }
        else if (elapsedMinutes > 0) {
            duration = elapsedMinutes + ":" + String.format("%02d", elapsedSeconds) + "." + elapsedMilliseconds;
        }
        else if (elapsedSeconds > 0) {
            duration = elapsedSeconds + "." + elapsedMilliseconds;
        }
        else {
            duration = "." + elapsedMilliseconds;
        }

        System.out.println("Result: " + result + ", Duration: " + duration);
    }
}
