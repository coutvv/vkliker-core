package ru.coutvv.vkliker.core.app;

import org.testng.annotations.Test;
import ru.coutvv.vkliker.core.App;
import ru.coutvv.vkliker.core.api.support.Delay;

import java.util.concurrent.Executors;

/**
 * @author coutvv    18.04.2018
 */
public class TestLimitlessLike extends TestApp {

    @Test
    public void testApp() throws Exception {
        App app = new LimitlessLike(testProps());

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                app.run();
            } catch (Exception e) {
                // once recovery place
            }
        });

        new Delay(10_000).apply(); // wait a little and turn app off
        app.control().off();
    }
}
