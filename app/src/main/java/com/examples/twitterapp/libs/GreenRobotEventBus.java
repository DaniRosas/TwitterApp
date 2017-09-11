package com.examples.twitterapp.libs;


import com.examples.twitterapp.libs.base.Eventbus;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by DaniRosas on 9/9/17.
 */

public class GreenRobotEventBus implements Eventbus {
    EventBus eventBus;

    private static class SingletonHolder {
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    public static GreenRobotEventBus getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public GreenRobotEventBus(){
        eventBus = org.greenrobot.eventbus.EventBus.getDefault();
    }

    public void register(Object subscriber){
        eventBus.register(subscriber);
    }

    public void unregister(Object subscriber){
        eventBus.unregister(subscriber);
    }

    public void post(Object event){
        eventBus.post(event);
    }
}
