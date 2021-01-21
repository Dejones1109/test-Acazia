package com.its.sanve.api.services;

import com.its.sanve.api.entities.Product;
import com.its.sanve.api.repositories.ProductRepository;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ProductService {
    @Autowired
    ProductRepository repository;

    public List<Product> computingByPriceAndName(List<Product> products) {
        List<Product> newList = new ArrayList<>();
        int size = products.size();
        Double[] stt = new Double[size];
        for (int i = 0; i < size; i++) {
            stt[i] = products.get(i).getPrice();
        }
        int length = stt.length;

        //sort by price
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++)
                if (stt[j] < stt[j + 1]) {
                    // swap stt[j+1] and stt[j]
                    double temp = stt[j];
                    stt[j] = stt[j + 1];
                    stt[j + 1] = temp;
                }
        }
         //sort by Alphabet
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                Product[] listProduct = repository.getProduct(stt[i]);
                if (listProduct.length == 1) {
                    newList.add(listProduct[0]);
                } else {
                    String[] data = new String[listProduct.length];
                    for (int j = 0; j < listProduct.length; j++) {
                        data[j] = listProduct[j].getName();
                    }
                    sortByAlphabet(data);
                    for (int j = 0; j < data.length; j++) {
                        newList.add(repository.product(data[j]));
                    }
                }
            } else {
                if (stt[i] < stt[i - 1]) {
                    Product[] listProduct = repository.getProduct(stt[i]);
                    if (listProduct.length == 1) {
                        newList.add(listProduct[0]);
                    } else {
                        String[] data = new String[listProduct.length];
                        for (int j = 0; j < listProduct.length; j++) {
                            data[j] = listProduct[j].getName();
                        }
                        sortByAlphabet(data);
                        for (int j = 0; j < data.length; j++) {
                            newList.add(repository.product(data[j]));
                        }
                    }
                }
            }
        }
        return newList;

    }

    private String[] sortByAlphabet(String[] names) {
        String temp;
        for (int i = 0; i < names.length; i++) {
            for (int j = 1; j < names.length; j++) {
                if (names[j - 1].compareTo(names[j]) > 0) {
                    temp = names[j - 1];
                    names[j - 1] = names[j];
                    names[j] = temp;
                }
            }
        }
        return names;
    }

}
