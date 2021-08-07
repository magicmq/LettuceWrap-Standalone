package dev.magicmq.lettucewrap.bungee;

import dev.magicmq.lettucewrap.RedisClientWrapper;
import dev.magicmq.lettucewrap.RedisListenerWrapper;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * A wrapper class that wraps lettuce's RedisClient class. This class should only be used with BungeeCord plugins.
 * @see RedisClientWrapper
 */
public class BungeeClientWrapper extends RedisClientWrapper<Plugin> {

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     */
    public BungeeClientWrapper(Plugin owner, String host) {
        super(owner, host);
    }

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     * @param port The port of the redis server to connect to, default is 6379
     */
    public BungeeClientWrapper(Plugin owner, String host, int port) {
        super(owner, host, port);
    }

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     * @param password The password of the redis server, or null if there is no password
     */
    public BungeeClientWrapper(Plugin owner, String host, String password) {
        super(owner, host, password);
    }

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     * @param port The port of the redis server to connect to, default is 6379
     * @param password The password of the redis server, or null if there is no password
     */
    public BungeeClientWrapper(Plugin owner, String host, int port, String password) {
        super(owner, host, port, password);
    }

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     * @param port The port of the redis server to connect to, default is 6379
     * @param password The password of the redis server, or null if there is no password
     * @param database The database that should be connected to on the redis server, default is 0
     */
    public BungeeClientWrapper(Plugin owner, String host, int port, String password, int database) {
        super(owner, host, port, password, database);
    }

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     * @param port The port of the redis server to connect to, default is 6379
     * @param password The password of the redis server, or null if there is no password
     * @param database The database that should be connected to on the redis server, default is 0
     * @param options Any special options that should be included in the redis URL. Format for each option should be "option=value", default is null (no options)
     */
    public BungeeClientWrapper(Plugin owner, String host, int port, String password, int database, String... options) {
        super(owner, host, port, password, database, options);
    }
}
