package com.examples.twitterapp.libs.base;

/**
 * Created by DaniRosas on 9/9/17.
 */

public interface Eventbus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
