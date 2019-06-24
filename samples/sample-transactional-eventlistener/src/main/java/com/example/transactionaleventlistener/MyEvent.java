package com.example.transactionaleventlistener;

import org.springframework.context.ApplicationEvent;

class MyEvent extends ApplicationEvent {

    MyEvent(Object source) {
        super(source);
    }
}
