package herokuapp_smoketest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({C01_CreateBooking.class,
        C02_GetCreateBooking.class,C03_UpdatecreateBooking.class})

public class Runner {


}
