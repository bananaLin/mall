package com.mmall.controller.app;

@Controller
@RequestMapping("/app/shipping")
public class ShippingController {

    @Autowrite
    ShippingService shippingService;
  
    @RequestMapping("/add.do")
    public Msg add(HttpSession httpSession, Shipping shpping){
        if(shpping == null){
            return null;
        }
        boolean result = shippingService.add(shpping);
        return null;
    }
}
