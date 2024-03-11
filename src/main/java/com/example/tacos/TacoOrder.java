package com.example.tacos;
import java.util.List;
import java.util.ArrayList;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
public class TacoOrder {
    @NotBlank(message="Delivery name is required")
    private String deliveryName;
    @NotBlank(message="Street is required")
    private String deliveryStreet;
    @NotBlank(message="City is required")
    private String deliveryCity;
    @NotBlank(message="State is required")
    private String deliveryState;
    @NotBlank(message="Zip code is required")
    private String deliveryZip;
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction=0, message="Invalid CVV")
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
