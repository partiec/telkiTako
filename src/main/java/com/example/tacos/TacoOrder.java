package com.example.tacos;
import java.util.List;
import java.util.ArrayList;

import lombok.Data;
@Data
public class TacoOrder {
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private List<Taco> tacos = new ArrayList<>();
    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}












//original------------>
// package com.example.taco;
//
//import lombok.Data;
//import com.example.taco.tacos.Taco;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Data
//public class TacoOrder {
//
//    private String deliveryName;
//    private String deliveryStreet;
//    private String deliveryCity;
//    private String deliveryState;
//    private String deliveryZip;
//    private String ccNumber;
//    private String ccExpiration;
//    private String ccCVV;
//
//    private List<Taco> tacos = new ArrayList<>();
//
//    public void addTaco(Taco taco) {
//        this.tacos.add(taco);
//    }
//}
