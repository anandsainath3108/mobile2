package com.capgemini.mps.exception;

public class MobilePurchaseException extends Exception{
    
    private String status;
    
public MobilePurchaseException()
{
    status="Mobile Purchase Exception";
}

public MobilePurchaseException(String status) {//right click source gentrate constuructor with feilds
    super();
    this.status = status;
}

@Override
public String toString() {
    return "MobilePurchaseException [status=" + status + "]";
}

public String getStatus() {//genrate getter alone select only get method
    return status;
}

  

}
