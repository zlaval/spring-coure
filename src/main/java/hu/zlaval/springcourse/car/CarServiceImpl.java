package hu.zlaval.springcourse.car;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//TODO as singleton
//TODO as protorype
//TODO as request
//TODO mention application,session,websocket
//TODO as interface
//TODO proxy modes, inject different proxies
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CarServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Getter
    private List<String> cars = new ArrayList<>();

    public CarServiceImpl() {
        logger.info("new car service instance is being created");
    }

    public void addCar(String name) {
        cars.add(name);
    }


}
