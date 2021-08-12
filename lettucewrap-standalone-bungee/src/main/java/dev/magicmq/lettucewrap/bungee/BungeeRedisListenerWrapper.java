package dev.magicmq.lettucewrap.bungee;

import dev.magicmq.lettucewrap.RedisListenerWrapper;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

/**
 * A wrapper designed to simplify receiving messages from redis PubSub. Handling of asynchronous message receiving to synchronous method calling is done automatically. The {@link RedisListenerWrapper#messageReceived(String)} method is called synchronously. This class should only be used with BungeeCord plugins.
 * @see RedisListenerWrapper
 */
public abstract class BungeeRedisListenerWrapper extends RedisListenerWrapper<Plugin> {

    public BungeeRedisListenerWrapper(String channel) {
        super(channel);
    }

    public void message(String channel, String message) {
        if (getChannel().equals(channel)) {
            ProxyServer.getInstance().getScheduler().schedule(getOwner(), () -> messageReceived(message), 0L, TimeUnit.MILLISECONDS);
        }
    }
}
