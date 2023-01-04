package org.paulfaa.Util;

import org.paulfaa.Model.Good;
import org.paulfaa.Model.Receipt;

public final class PrinterUtil {

    public static void printReceipt(Receipt receipt){
        for (Good good : receipt.getGoods()) {
            System.out.println(good.toString());
        }
        System.out.println("Sales Taxes: " + receipt.getSalesTaxes());
        System.out.println("Total: " + receipt.getTotal());
    }

    public static void printReceipts(Receipt[] receipts){
        for (Receipt receipt : receipts) {
            printReceipt(receipt);
        }
    }
}
