package ru.napadovskiu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.napadovskiu.entities.Car;
import ru.napadovskiu.storage.CarStorage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showItems(){
        return "items";
    }

    @RequestMapping(value = "/userServlet", method = RequestMethod.GET)
    @ResponseBody
    public List<Car> ajaxTest() {
        CarStorage carStorage = CarStorage.getInstance();
        List<Car> list = carStorage.getAllCarName();
//        Set<String> records = new HashSet<String>();
//        records.add("Record #1");
//        records.add("Record #2");

        return list;
    }

}
