package zaurek.listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.inventory.ItemStack;

public class BottleListener implements Listener {

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		//check if xpjar command is used
		if(!command.getName().equalsIgnoreCase("xpjar")){
			return false;
		}
		//check if sender is a player
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		} else {
			sender.sendMessage("Command can only used by a player");
			return true;
		}
		
		//check if args != null
		if( args.length == 0){
			sender.sendMessage("/xpjar <ammount>");
			return true;
		}
		
		//check if arguments are correct
		if(args.length != 1 || !args[0].matches("\\d+")){
			sender.sendMessage("Invalid arguments");
			return true;
		}
		
		int ammount = Integer.valueOf(args[0]);
		
		//check players inventory for glass bottles
		if(!player.getInventory().contains(374)){
			sender.sendMessage("Get glass bottles first.");
			return true;
		}
		
		//check for player XP
		int playerXP = player.getTotalExperience();
		if(playerXP<ammount){
			sender.sendMessage("Not enought XP.");
			return true;
		}
		
		//check if ammount fits in a short
		if ( ammount > Short.MAX_VALUE){
			sender.sendMessage("Max XP in a bottle is " + Short.MAX_VALUE);
			return true;
		}
		
		bottleXP(player, (short) ammount);
	
		return true;
	}
	
	@EventHandler
	public void onBottleBreak(ExpBottleEvent event){
		event.setExperience(((Item)event.getEntity()).getItemStack().getDurability());
	}

	private void bottleXP(Player player, short ammount) {
		player.getInventory().remove(new ItemStack(374, 1));
		player.getInventory().addItem(new ItemStack(384, 1, ammount));
		player.setTotalExperience(player.getTotalExperience() - ammount);
	}
}
