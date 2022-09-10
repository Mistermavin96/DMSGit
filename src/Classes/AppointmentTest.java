package Classes;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    String tz = "0:00-05:00[America/New_York]";
    String ds = "2022-09-1";
    String x;
    String y;

    @Test
    void inHoursValidator1() {
            String x = "0T09:0";
            String y = "0T10:0";

            if (!Appointment.inHoursValidator(ZonedDateTime.parse(ds+x+tz),ZonedDateTime.parse(ds+y+tz))) {
                fail();
            }
        }

    @Test
    void inHoursValidator2() {
            x="0T21:0";
            y="1T07:3";
        if(Appointment.inHoursValidator(ZonedDateTime.parse(ds+x+tz),ZonedDateTime.parse(ds+y+tz))) {
            fail();
        }
    }

    @Test
    void inHoursValidator3() {
        x = "0T21:0";
        y = "1T07:0";
        if(Appointment.inHoursValidator(ZonedDateTime.parse(ds+x+tz),ZonedDateTime.parse(ds+y+tz))) {
            fail();
        }
    }

    @Test
    void inHoursValidator4() {
        x="0T20:3";
        y="1T07:0";
        if(Appointment.inHoursValidator(ZonedDateTime.parse(ds+x+tz),ZonedDateTime.parse(ds+y+tz))) {
            fail();
        }
    }

    @Test
    void inHoursValidator5() {
        x = "0T06:3";
        y = "0T07:3";
        if(Appointment.inHoursValidator(ZonedDateTime.parse(ds+x+tz),ZonedDateTime.parse(ds+y+tz))) {
            fail();
        }
    }

    @Test
    void inHoursValidator6() {
        x="0T07:0";
        y="1T07:0";
        if(Appointment.inHoursValidator(ZonedDateTime.parse(ds+x+tz),ZonedDateTime.parse(ds+y+tz))) {
            fail();
        }
    }

}