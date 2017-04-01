package track.container;

import track.container.config.Bean;
import track.container.beans.Car;
import track.container.beans.Gear;
import track.container.config.ConfigReader;
import track.container.config.InvalidConfigurationException;

import java.util.List;
import java.io.File;


/**
 *
 */
public class Main {

    public static void main(String[] args) {
        List<Bean> beans;
        ConfigReader reader = new JsonConfigReader();
        try {
            beans = reader.parseBeans(new File("config.json"));


            Container container = new Container(beans);
            Car car ;
            Gear gear ;
            gear = (Gear) container.getByid("gearBean");
            System.out.println(gear.toString());
            car = (Car) container.getByClass("track.container.beans.Car");
            System.out.println(car.toString());
            System.out.println(gear.equals(car.getGear()));
        } catch (BeanException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


}
