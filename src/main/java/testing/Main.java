package testing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

/* 1. Создать класс, который может выполнять «тесты»,
      в качестве тестов выступают классы с наборами методов с аннотациями @Test.
      Для этого у него должен быть статический метод start(),
      которому в качестве параметра передается или объект типа Class,
      или имя класса. Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется,
      далее запущены методы с аннотациями @Test, а по завершению всех тестов – метод с аннотацией @AfterSuite.
      К каждому тесту необходимо также добавить приоритеты (int числа от 1 до 10),
      в соответствии с которыми будет выбираться порядок их выполнения, если приоритет одинаковый,
      то порядок не имеет значения.
      Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре,
      иначе необходимо бросить RuntimeException при запуске «тестирования».

      Это домашнее задание никак не связано с темой тестирования через JUnit и использованием
      этой библиотеки, то есть проект пишется с нуля.
* */

     public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
             InstantiationException, IllegalAccessException, InvocationTargetException {
         Animals animals = new Animals();
         Class Animals = animals.getClass();
         int maxPriority = 10;
         String className = "testing.Animals";

         start(Animals, maxPriority);

         start(className, maxPriority);
     }

    private static void start(Class animal, int maxPriority) throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
         Object testObj = animal.getDeclaredConstructor().newInstance();
        Method[] methods = testObj.getClass().getDeclaredMethods();
        //ghbdtdgdhsfh
        // делаем проверку что @BeforeSuite и @AfterSuite в единственном экзепляре
        int before = 0;
        int after = 0;

        for (Method  metod : methods) {
            if(metod.isAnnotationPresent(BeforeSuite.class)){
                if (before == 0) {
                    before++;
                } else {
                    throw new RuntimeException(" Метод с аннотацией @BeforeSuite не одном экзепляре " +
                            "/Method with @BeforeSuite annotation in more than one instance ");
                }
            }
            if(metod.isAnnotationPresent(AfterSuite.class)){
                if (after == 0) {
                    after++;
                } else {
                    throw new RuntimeException(" Метод с аннотацией @AfterSuite не одном экзепляре " +
                            "/Method with @AfterSuite annotation in more than one instance ");
                }
            }
        }
        
        // 
        for (Method metodTest: methods) {
            if(metodTest.isAnnotationPresent(BeforeSuite.class)){
                metodTest.invoke(testObj);
            }
        }
        for (int i = maxPriority; i >= 0 ; i--) {
            for (Method metodTest: methods) {
                if (metodTest.isAnnotationPresent(Test.class)){
                    if(metodTest.getAnnotation(Test.class) != null){
                        if(metodTest.getAnnotation(Test.class).priority() == i) {
                            metodTest.invoke(testObj);
                        }
                    }
                }
            }
        }

        for (Method metodTest: methods) {
            if (metodTest.isAnnotationPresent(AfterSuite.class)){
                metodTest.invoke(testObj);
            }
        }
        System.out.println();
    }

    public static void start(String className, int maxPriority) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        System.out.println("передали имя класса / passed the class name");
        Class Animal = Class.forName(className);
        Object testObj = Animal.getDeclaredConstructor().newInstance();

        Method[] methods = testObj.getClass().getDeclaredMethods();
        int before = 0;
        int after = 0;

        for (Method  metod : methods) {
            if(metod.isAnnotationPresent(BeforeSuite.class)){
                if (before == 0) {
                    before++;
                } else {
                    throw new RuntimeException(" Метод с аннотацией @BeforeSuite не одном экзепляре " +
                            "/Method with @BeforeSuite annotation in more than one instance ");
                }
            }
            if(metod.isAnnotationPresent(AfterSuite.class)){
                if (after == 0) {
                    after++;
                } else {
                    throw new RuntimeException(" Метод с аннотацией @AfterSuite не одном экзепляре " +
                            "/Method with @AfterSuite annotation in more than one instance ");
                }
            }
        }
        // запускаем сначала
        for (Method metodTest: methods) {
            if(metodTest.isAnnotationPresent(BeforeSuite.class)){
                metodTest.invoke(testObj);
            }
        }

        for (int i = maxPriority; i >= 0 ; i--) {
            for (Method metodTest: methods) {
                if (metodTest.isAnnotationPresent(Test.class)){
                    if(metodTest.getAnnotation(Test.class) != null){
                        if(metodTest.getAnnotation(Test.class).priority() == i) {
                            metodTest.invoke(testObj);
                        }
                    }
                }
            }
        }

        for (Method metodTest: methods) {
            if (metodTest.isAnnotationPresent(AfterSuite.class)){
                metodTest.invoke(testObj);
            }
        }
    }

}