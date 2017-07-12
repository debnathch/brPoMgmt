package util;

/**
 * Created by debnathchatterjee on 12/07/17.
 */
public enum puchaseOrderConstant {

     isCart("CART"),
     isOrder("ORDER"),
    isActivate("Y"),
    isDeActivate("N");

    private String constParam;

    puchaseOrderConstant(String constParam) {
        this.constParam = constParam;
    }

    public String constParam() {
        return constParam;
    }

}
