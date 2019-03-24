package ua.net.kurpiak.restapiexplorer.reflection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.net.kurpiak.restapiexplorer.meta.ApiDescription;
import ua.net.kurpiak.restapiexplorer.meta.ApiEndpoint;
import ua.net.kurpiak.restapiexplorer.pojo.MethodDescription;
import ua.net.kurpiak.restapiexplorer.reflection.methods.ClassWithMethods;
import ua.net.kurpiak.restapiexplorer.reflection.methods.ClassWithoutMethods;
import ua.net.kurpiak.restapiexplorer.reflection.testdata.pckg_1.Class1;
import ua.net.kurpiak.restapiexplorer.reflection.testdata.pckg_1.Class2;
import ua.net.kurpiak.restapiexplorer.reflection.testdata.pckg_1.sub_pckg_1.SubClass1;
import ua.net.kurpiak.restapiexplorer.reflection.testdataextends.BaseClass;
import ua.net.kurpiak.restapiexplorer.reflection.testdataextends.ChildClassAnnotationOverride;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.*;
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

    @Test
    public void testSelectAllClassesWithPassedAnnotation() {
        List<Class<?>> classes = asList(Class1.class, Class2.class);
        List<Class<?>> apiDescriptionClasses = ReflectionUtils.getAllClassesWithAnnotation(classes, ApiDescription.class);

        assertThat(apiDescriptionClasses, hasSize(1));
        assertThat(apiDescriptionClasses.get(0), equalTo(Class1.class));
    }

    @Test
    public void testSelectAllClassesWhenExtends() {
        List<Class<?>> classes = ReflectionUtils.getAllClassesInPackage("ua.net.kurpiak.restapiexplorer.reflection.testdataextends");

        assertThat(classes, hasSize(4));

        List<Class<?>> classesWithAnnotation = ReflectionUtils.getAllClassesWithAnnotation(classes, ApiDescription.class);

        assertThat(classesWithAnnotation, hasSize(2));
        assertThat(classesWithAnnotation, containsInAnyOrder(BaseClass.class, ChildClassAnnotationOverride.class));
    }

    @Test
    public void testGetAnnotationNullNotAllowedAsClass() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Class can't be null");

        ReflectionUtils.getAnnotation(null, ApiDescription.class);
    }

    @Test
    public void testGetAnnotationNullNotAllowedAsAnnotation() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Annotation can't be null");

        ReflectionUtils.getAnnotation(Class1.class, null);
    }

    @Test
    public void testCanGetAnnotationInfoFromClassWithItsData() {
        ApiDescription annotation = ReflectionUtils.getAnnotation(Class1.class, ApiDescription.class);

        assertThat(annotation, notNullValue());
        assertThat(annotation.path(), is("test"));
        assertThat(annotation.description(), is(""));
    }

    @Test
    public void testCanGetAnnotatedMethods() {
        List<MethodDescription<ApiEndpoint>> methods = ReflectionUtils.getAllMethodsWithAnnotation(ClassWithMethods.class, ApiEndpoint.class);

        assertThat(methods, hasSize(2));
    }

    @Test
    public void testClassWithoutPublicMethods() {
        List<MethodDescription<ApiEndpoint>> methods = ReflectionUtils.getAllMethodsWithAnnotation(ClassWithoutMethods.class, ApiEndpoint.class);

        assertThat(methods, is(emptyList()));
    }

}
