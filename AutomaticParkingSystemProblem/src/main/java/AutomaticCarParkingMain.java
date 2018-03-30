



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import com.java.bean.CarParkingBean;

@SpringBootApplication
public class AutomaticCarParkingMain {
	
	
	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(AutomaticCarParkingMain.class, args);
		CarParkingBean autoparking = context.getBean(CarParkingBean.class);
		
		for (int i = 0; i < args.length; i++) {
			autoparking.calculateNewPosition(args[i]);
		}
	}

}
