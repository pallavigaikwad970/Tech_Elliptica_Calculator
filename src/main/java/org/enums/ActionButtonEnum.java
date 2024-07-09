package org.enums;


import org.openqa.selenium.By;

import static org.pageobject.CalculatorScreenPageObject.*;

public enum ActionButtonEnum {
    Add("add", btn_addition),
    Sub("sub",btn_minus),
    Div("div",btn_division),
    Mul("mul",btn_multiply),
    Equals("equals",btn_equals),
    Dot("dot",btn_dot),
    clear("clear",btn_clear);

    String btnName;
    By elementLocator;
    ActionButtonEnum(String btnName, By elementLocator ){
        this.btnName = btnName;
        this.elementLocator = elementLocator;
    }

    public By getElementLocator(){
        return this.elementLocator;
    }
    public String getBtnName(){
        return this.btnName;
    }
}
