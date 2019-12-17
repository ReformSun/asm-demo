package com.test.testclass;

import com.test.annotation.Test;

public class SubTestClass<T> {
    @Test(id=1)
    public  T testMethod(){
        return null;
    }
}
