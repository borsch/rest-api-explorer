package ua.net.kurpiak.restapiexplorer.ui;

import org.junit.Test;
import ua.net.kurpiak.restapiexplorer.RestApiExplorer;

public class UiBuilderTest {

    @Test
    public void testGenerateHtml() {
        RestApiExplorer.init("ua.net.kurpiak.restapiexplorer.ui.controllers");
    }

}
