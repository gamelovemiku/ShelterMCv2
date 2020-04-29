package com.gamelovemiku.sheltermc.global;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import me.vagdedes.mysql.database.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ExampleGUI implements InventoryHolder, Listener
{
    private final Inventory inv;
    private long eggStock = 0;

    public ExampleGUI()
    {
        // Create a new inventory, with "this" owner for comparison with other inventories, a size of nine, called example
        inv = Bukkit.createInventory(this, 9*2, "Farm Exporter");

        // Put the items into the inventory
        initializeItems();
    }

    @Override
    public Inventory getInventory()
    {
        return inv;
    }

    // You can call this whenever you want to put the items in
    public void initializeItems()
    {
        inv.setItem(3, createGuiItem(
                Material.EGG,
                "§bStock: Egg",
                "§r",
                "§9ปริมาณที่รับได้",
                "§6" + eggStock + "/300",
                "§r",
                "§9ราคารับซื้อ",
                "§e25 Cubix / 16 ชิ้น",
                "§r",
                "§9เวลาส่งออกครั้งต่อไป",
                "§e00:00:00",
                "§r",
                "§dคลิ๊กเพื่อส่งออกสินค้า",
                "§7ต้องการขั้นต่ำ 16 ชิ้น"
        ));
    }

    // Nice little method to create a gui item with a custom name, and description
    protected ItemStack createGuiItem(final Material material, final String name, final String... lore)
    {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    // You can open the inventory with this
    public void openInventory(final HumanEntity ent)
    {
        ent.openInventory(inv);
        ent.sendMessage("OPEND");
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event)
    {
        if (!event.getView().getTitle().equalsIgnoreCase("Farm Exporter")) return;

        event.setCancelled(true);

        event.getWhoClicked().sendMessage("CLICKED " + event.getInventory().getHolder().toString());
        event.getWhoClicked().sendMessage("SLOT: " + event.getRawSlot());

        try {
            event.getWhoClicked().sendMessage("TYPE: " + event.getCurrentItem().getType());
        } catch (NullPointerException error) {
            event.getWhoClicked().sendMessage("TYPE: -");
        }

        ItemStack clickedItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();

        if(clickedItem.getType().equals(Material.EGG)) {
            if(event.getClick().equals(ClickType.LEFT)) {
                event.getWhoClicked().sendMessage("VALUE: " + eggStock);
                PlayerInventory inventory = player.getInventory();
                if(inventory.contains(Material.EGG, 16)) {
                    player.sendMessage(ChatColor.GREEN + "You have egg x16");
                    inventory.removeItem(new ItemStack(Material.EGG, 16));
                    eggStock += 16;
                } else {
                    player.sendMessage(ChatColor.RED + "NEED EGG! x16");
                }
            }
            if(event.getClick().equals(ClickType.RIGHT)) {
                eggStock--;
                event.getWhoClicked().sendMessage("VALUE: " + eggStock);
            }

            try {
                MySQL.connect();
                if(MySQL.isConnected()) {
                    event.getWhoClicked().sendMessage("SQL CONNECTED");
                } else {
                    event.getWhoClicked().sendMessage("SQL NOOOOOOOOOO");
                }
            } catch(Exception error) {
                error.printStackTrace();
            }

            openInventory(event.getWhoClicked());
            initializeItems();
        }
    }
}