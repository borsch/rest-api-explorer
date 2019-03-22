package ua.net.kurpiak.restapiexplorer.reflection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.net.kurpiak.restapiexplorer.reflection.testdata.pckg_1.Class1;
import ua.net.kurpiak.restapiexplorer.reflection.testdata.pckg_1.Class2;
import ua.net.kurpiak.restapiexplorer.reflection.testdata.pckg_1.sub_pckg_1.SubClass1;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ReflectionUtilsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testReadNullPackageMustThrowException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Package name can't be null");

        ReflectionUtils.getAllClassesInPackage(null);
    }

    @Test
    public void testCanReadAllClassesInPackageAndSubpackages() {
        List<Class<?>> classes = ReflectionUtils.getAllClassesInPackage("ua.net.kurpiak.restapiexplorer.reflection.testdata");

        assertThat(classes, hasSize(3));
        assertThat(classes, containsInAnyOrder(Class1.class, SubClass1.class, Class2.class));
    }

}
