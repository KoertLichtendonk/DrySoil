package nl.koertlichtendonk.spigotplugin.drysoil;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DrySoil extends JavaPlugin implements Listener
{
    @Override
    public void onEnable() {

        getLogger().info("DrySoil has been enabled!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable(){

    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event)
    {
        if (event.getNewState().getBlock().getType().equals(Material.FARMLAND)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        //Define the player
        Player player = event.getPlayer();
        //Define the item in their hand
        Material mat = player.getInventory().getItemInMainHand().getType();

        //If the player right clicks a block
        if(event.getAction() == Action.LEFT_CLICK_BLOCK) {

            //Get clicked block
            Block clicked_block = event.getClickedBlock();
            Material clicked_block_mat = clicked_block.getType();

            // Is clicked block soil? Turn to dirt.
            if(clicked_block_mat == Material.FARMLAND) {

                //Check if they clicked with a shovel
                if (mat.toString().endsWith("HOE")) {
                    clicked_block.setType(Material.DIRT);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent event)
    {
        //Define the player
        Player player = event.getPlayer();
        //Define the item in their hand
        Material mat = player.getInventory().getItemInMainHand().getType();
        //Get clicked block
        Block block = event.getBlock();
        Material block_mat = block.getType();

        // Is clicked block soil? Turn to dirt.
        if(block_mat == Material.FARMLAND) {

            //Check if they clicked with a shovel
            if (mat.toString().endsWith("HOE")) {
                event.setCancelled(true);
            }
        }
    }
}
