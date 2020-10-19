package testing;

public class Animals {

    @BeforeSuite(priority = 1)
    public void BeforeSuiteTest(){
        System.out.println("метод BeforeSuite / Metod BeforeSuite");
    }

    @AfterSuite(priority = 10)
    public void AfterSuiteTest(){
        System.out.println("метод AfterSuite / Metod AfterSuite");
    }

    @Test()
    public void jumpTest(){
        System.out.println("Животное прыгнуло / The animal jumped");
    }

    @Test(priority = 3)
    public void runTest(){
        System.out.println("Животное побежало / The animal ran");
    }

    @Test(priority = 2)
    public void eatTest(){
        System.out.println("Живтоное покушало / The animal ate");
    }

    @Test(priority = 9)
    public void sleepTest(){
        System.out.println("Животное спит / The animal is sleeping");
    }

    @Test(priority = 4)
    public void relaxTest(){
        System.out.println("Живтное отдыхает / The animal relaxing");
    }

    @Test(priority = 6)
    public void speedRaceTest(){
        System.out.println("Забег на скорость / Speed Run");
    }
}
