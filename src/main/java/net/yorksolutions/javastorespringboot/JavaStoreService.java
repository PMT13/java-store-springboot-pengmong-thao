package net.yorksolutions.javastorespringboot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

@Service
public class JavaStoreService {

    private ArrayList<Product> stock;

    public JavaStoreService() {
        this.stock = new ArrayList<Product>();
    }

    public void addProduct(Product product){
        this.stock.add(product);
    }

    public void removeProduct(long id){
        for(int i = 0; i < this.stock.size(); i++){
            if(this.stock.get(i).getId() == id){
                this.stock.remove(i);
                break;
            }
        }
    }

    public void replaceProduct(long id, Product product){
        for(int i = 0; i < this.stock.size(); i++){
            if(this.stock.get(i).getId() == id){
                this.stock.set(i,product);
                break;
            }
        }
    }

    public void lowerThan(double price){
        ArrayList<Product> newStock = new ArrayList<>();
        for(int i = 0; i < this.stock.size(); i++){
            if(this.stock.get(i).getCurrentPrice() < price){
                newStock.add(this.stock.get(i));
            }
        }
    }

    public void greaterThan(double price){
        ArrayList<Product> newStock = new ArrayList<>();
        for(int i = 0; i < this.stock.size(); i++){
            if(this.stock.get(i).getCurrentPrice() > price){
                newStock.add(this.stock.get(i));
            }
        }
    }

    public HashMap getStats(){
        HashMap map = new HashMap();
        double median;
        double average = 0;
        this.stock = this.sortByPrice(this.stock);
        if (this.stock.size() % 2 == 0) {
            median = (this.stock.get(this.stock.size() / 2).getCurrentPrice() + this.stock.get(this.stock.size() / 2 - 1).getCurrentPrice()) / 2;
        }else {
            median = this.stock.get(this.stock.size() / 2).getCurrentPrice();
        }
        for(int i = 0; i < this.stock.size(); i++){
            average += this.stock.get(i).getCurrentPrice();
        }
        average = average / this.stock.size();
        map.put("median",median);
        map.put("average",average);
        return map;
    }

    public HashMap display(){
        HashMap bigMap = new HashMap();
        this.stock = sortByPrice(this.stock);
        for(int i = 0; i < this.stock.size(); i++){
            HashMap map = new HashMap();
            map.put("id",this.stock.get(i).getId());
            map.put("name",this.stock.get(i).getDisplay_name());
            map.put("current_price", this.stock.get(i).getCurrentPrice());
            map.put("original_price",this.stock.get(i).getPrice());
            bigMap.put(i,map);
        }
        return bigMap;
    }

    public ArrayList<Product> sortByPrice(ArrayList<Product> productList){
        //https://www.baeldung.com/java-8-comparator-comparing
        Comparator<Product> priceComparator = Comparator.comparing(Product::getCurrentPrice);
        productList.sort(priceComparator);
        return productList;
    }

    public void sale(double price, boolean onSale){
        for(int i = 0; i < this.stock.size(); i++){
            if(this.stock.get(i).getCurrentPrice() > price){
                this.stock.get(i).setOnSale(onSale);
            }
        }
    }
}
