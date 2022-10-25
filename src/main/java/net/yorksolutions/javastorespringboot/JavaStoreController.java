package net.yorksolutions.javastorespringboot;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class JavaStoreController {

    private JavaStoreService javaStoreService;

    public JavaStoreController(JavaStoreService javaStoreService){
        this.javaStoreService = javaStoreService;
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product newProduct){
        this.javaStoreService.addProduct(newProduct);
        return newProduct;
    }

    @DeleteMapping("/remove/{id}")
    public void removeProduct(@PathVariable long id){
        this.javaStoreService.removeProduct(id);
    }

    @PutMapping("/replace/{id}")
    public void replaceProduct(@PathVariable long id, @RequestBody Product newProduct){
        this.javaStoreService.replaceProduct(id,newProduct);
    }

    @GetMapping("/stats")
    public HashMap getStats(){
        return this.javaStoreService.getStats();
    }

    @GetMapping("/display")
    public HashMap display(){
        return this.javaStoreService.display();
    }

    @GetMapping("/sale")
    public HashMap displaySale(@RequestParam String dollars,@RequestParam String cents,@RequestParam boolean onSale){
        String stringPrice = "" + dollars + "." + cents + "D";
        double price = Double.parseDouble(stringPrice);
        this.javaStoreService.sale(price,onSale);
        return this.javaStoreService.display();
    }
}
