package ru.coutvv.vkliker.core.app;

import org.cactoos.io.InputStreamOf;
import org.cactoos.io.ResourceOf;
import org.cactoos.scalar.PropertiesOf;
import org.cactoos.text.TextOf;
import org.testng.annotations.Test;
import ru.coutvv.vkliker.core.App;
import ru.coutvv.vkliker.core.api.support.Delay;

import java.util.concurrent.Executors;

/**
 * @author coutvv    02.07.2018
 */
public class TestLikeAround {

    @Test
    public void testApp() throws Exception {
        App app = new LikeAround(new PropertiesOf(
                new TextOf(
                        new InputStreamOf(
                                new ResourceOf("app.properties")
                        )
                )
        ).value());

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                app.run();
            } catch (Exception e) {
                // once recovery place
            }
        });

        new Delay(100_000).apply(); // wait a little and turn app off
        app.control().off();
    }

}
