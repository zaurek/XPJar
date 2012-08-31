package zaurek;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import zaurek.listeners.BottleListener;

public class XPJar extends JavaPlugin {

	private BottleListener bottleListener = new BottleListener();

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(bottleListener, this);
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		return bottleListener.onCommand(sender, command, label, args);
	}
}
