package dev.magicmq.lettucewrap.bukkit;

import dev.magicmq.lettucewrap.RedisListenerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A wrapper designed to simplify receiving messages from redis PubSub. Handling of asynchronous message receiving to synchronous method calling is done automatically. The {@link RedisListenerWrapper#messageReceived(String)} method is called synchronously. This class should only be used with Bukkit plugins.
 * @see RedisListenerWrapper
 */
public abstract class BukkitListenerWrapper extends RedisListenerWrapper<JavaPlugin> {

    public BukkitListenerWrapper(String channel) {
        super(channel);
    }

    public void message(String channel, String message) {
        if (getChannel().equals(channel)) {
            Bukkit.getScheduler().runTask(getOwner(), () -> messageReceived(message));
        }
    }
}
