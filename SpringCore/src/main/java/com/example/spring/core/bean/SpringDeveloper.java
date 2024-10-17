package com.example.spring.core.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class SpringDeveloper {

    private Address address;

    public SpringDeveloper(Address address) {
        this.address = address;
    }
}
