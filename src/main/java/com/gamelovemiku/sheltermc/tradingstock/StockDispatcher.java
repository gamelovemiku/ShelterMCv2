package com.gamelovemiku.sheltermc.tradingstock;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import me.vagdedes.mysql.database.MySQL;
import me.vagdedes.mysql.database.SQL;
import org.bukkit.Bukkit;

import java.util.List;

public class StockDispatcher {
    public int getItemStock(String item) {
        int total = 0;
        try {
            MySQL.connect();
            if(MySQL.isConnected()) {
                List<Object> objectList = SQL.listGet("total", "stock_item_name", "=", item, "trading_stock");
                total = Integer.parseInt(objectList.get(0).toString());
            }
            MySQL.disconnect();
        } catch(Exception  error) {
            new ShelterMCHelper().log("Cann\'t connect to database or something wrong..");
        }
        return total;
    }

    public int getPricePerStack(String item) {
        int total = 0;
        try {
            MySQL.connect();
            if(MySQL.isConnected()) {
                List<Object> objectList = SQL.listGet("price_per_stack", "stock_item_name", "=", item, "trading_stock");
                total = Integer.parseInt(objectList.get(0).toString());
            }
            MySQL.disconnect();
        } catch(Exception  error) {
            new ShelterMCHelper().log("Cann\'t connect to database or something wrong..");
        }
        return total;
    }

    public int getItemMaxHandle(String item) {
        int maxhandle = 0;
        try {
            MySQL.connect();
            if(MySQL.isConnected()) {
                List<Object> objectList = SQL.listGet("max_handle", "stock_item_name", "=", item, "trading_stock");
                maxhandle = Integer.parseInt(objectList.get(0).toString());
            }
            MySQL.disconnect();
        } catch(Exception  error) {
            new ShelterMCHelper().log("Cann\'t connect to database or something wrong..");
        }
        return maxhandle;
    }

    public boolean canAcceptMore(String item) {
        try {
            MySQL.connect();
            if(MySQL.isConnected()) {
                int total = Integer.parseInt(SQL.listGet("total", "stock_item_name", "=", item, "trading_stock").get(0).toString());
                int maxhandle = Integer.parseInt(SQL.listGet("max_handle", "stock_item_name", "=", item, "trading_stock").get(0).toString());
                Bukkit.broadcastMessage("RESULT: " + total + " / " + maxhandle);
                if(total >= maxhandle) {
                    return false;
                } else if(total < maxhandle) {
                    return true;
                }
            }
            MySQL.disconnect();
        } catch(Exception  error) {
            new ShelterMCHelper().log("Cann\'t connect to database or something wrong..");
        }
        return false;
    }

    public boolean addItemStock(String item, int amount) {
        int total = 0;
        try {
            MySQL.connect();
            if(MySQL.isConnected()) {
                total = Integer.parseInt(SQL.listGet("total", "stock_item_name", "=", item, "trading_stock").get(0).toString());
                SQL.set("total", total+amount, "stock_item_name", "=", item, "trading_stock");
                return true;
            }
            MySQL.disconnect();
        } catch(Exception  error) {
            new ShelterMCHelper().log("[OnAdd] Cann\'t connect to database or something wrong..");
            return false;
        }
        return false;
    }

    public boolean setItemStock(String item, int amount) {
        int total = 0;
        try {
            MySQL.connect();
            if(MySQL.isConnected()) {
                total = Integer.parseInt(SQL.listGet("total", "stock_item_name", "=", item, "trading_stock").get(0).toString());
                SQL.set("total", amount, "stock_item_name", "=", item, "trading_stock");
                return true;
            }
            MySQL.disconnect();
        } catch(Exception  error) {
            new ShelterMCHelper().log("[OnRemove] Cann\'t connect to database or something wrong..");
            return false;
        }
        return false;
    }

    public boolean removeItemStock(String item, int amount) {
        int total = 0;
        try {
            MySQL.connect();
            if(MySQL.isConnected()) {
                total = Integer.parseInt(SQL.listGet("total", "stock_item_name", "=", item, "trading_stock").get(0).toString());
                SQL.set("total", total-amount, "stock_item_name", "=", item, "trading_stock");
                return true;
            }
            MySQL.disconnect();
        } catch(Exception error) {
            new ShelterMCHelper().log("[OnRemove] Cann\'t connect to database or something wrong..");
            return false;
        }
        return false;
    }
}
