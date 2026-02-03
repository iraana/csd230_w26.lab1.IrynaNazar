package csd230.lab1.controllers;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/checkout")
    public String checkout() {


        Long defaultCartId = 1L;

        orderRepository.deleteAll();

        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);

        if (cart == null || cart.getProducts().isEmpty()) {
            return "redirect:/cart";
        }

        OrderEntity order = new OrderEntity();
        order.setTotalAmount(0);
        order.setOrderDate(LocalDateTime.now());

        double total = 0;


        for (ProductEntity product : cart.getProducts()) {


            total += product.getPrice();


            if (product instanceof PublicationEntity publication) {
                if (publication.getCopies() > 0) {
                    publication.setCopies(publication.getCopies() - 1);
                    bookRepository.save((BookEntity) publication);
                }
            }


            order.addProduct(product);
        }


        order.setTotalAmount(total);


        cart.getProducts().clear();


        orderRepository.save(order);
        cartRepository.save(cart);


        return "redirect:/order/confirmation";
    }

    @GetMapping("/confirmation")
    public String confirmation(Model model) {
        List<OrderEntity> orders  = orderRepository.findAll();
        if (orders == null  || orders.isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("order", orders.get(0));

        return "orderDetails";


    }
}
