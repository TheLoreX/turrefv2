package com.example.turrefv2.action;

public class ActionProvider {

    ButtonHandler buttonHandler;
    TouchHandler touchHandler;
    SwitchHandler switchHandler;
    AnimHandler animHandler;

    ActionProvider(){
        buttonHandler = new ButtonHandler(this);
        touchHandler = new TouchHandler(this);
        switchHandler = new SwitchHandler(this);
        animHandler = new AnimHandler(this);
    }

    public ButtonHandler buttonProvider(){
        return buttonHandler;
    }
}
