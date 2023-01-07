package controller;

import model.OrderDetail;
import model.Orders;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class OrderCrud {
    public boolean saveOrder(Orders orders) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO vehicle_service.orders VALUES (?,?,?)",
                orders.getId(),orders.getDate(),orders.getCustomerId());
    }

    public boolean saveOrderDetails(ArrayList<OrderDetail> details) throws SQLException, ClassNotFoundException {
        for (OrderDetail od:details
        ) {
            boolean isDetailSaved=CrudUtil.execute("INSERT INTO vehicle_service.orderdetail VALUES (?,?,?,?)",
                    od.getOderId(),od.getItemCode(),od.getQty(),od.getUnitPrice());
            if(isDetailSaved){
                if (!updateQty(od.getItemCode(),od.getQty())){
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String itemCode, String qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE vehicle_service.stock SET itemQty=itemQty-? WHERE itemId=?",qty,itemCode);
    }

    public static String getOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT oId FROM vehicle_service.orders ORDER BY oId DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return result.getString(1);
        }
        return null;
    }

    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String orderId = getOrderId();
        if (orderId != null) {
            String[] split = orderId.split("D0");
            int OrderID = Integer.parseInt(split[1]);
            OrderID += 1;
            return "D0" + OrderID;
        }
        return "D01";
    }



}



