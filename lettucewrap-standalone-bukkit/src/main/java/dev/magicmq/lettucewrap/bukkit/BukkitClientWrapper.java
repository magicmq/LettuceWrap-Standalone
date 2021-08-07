package dev.magicmq.lettucewrap.bukkit;

import dev.magicmq.lettucewrap.RedisClientWrapper;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A wrapper class that wraps lettuce's RedisClient class. This class should only be used with Bukkit plugins.
 * @see RedisClientWrapper
 */
public class BukkitClientWrapper extends RedisClientWrapper<JavaPlugin> {

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     */
    public BukkitClientWrapper(JavaPlugin owner, String host) {
        super(owner, host);
    }

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     * @param port The port of the redis server to connect to, default is 6379
     */
    public BukkitClientWrapper(JavaPlugin owner, String host, int port) {
        super(owner, host, port);
    }

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     * @param password The password of the redis server, or null if there is no password
     */
    public BukkitClientWrapper(JavaPlugin owner, String host, String password) {
        super(owner, host, password);
    }

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     * @param port The port of the redis server to connect to, default is 6379
     * @param password The password of the redis server, or null if there is no password
     */
    public BukkitClientWrapper(JavaPlugin owner, String host, int port, String password) {
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
    public BukkitClientWrapper(JavaPlugin owner, String host, int port, String password, int database) {
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
    public BukkitClientWrapper(JavaPlugin owner, String host, int port, String password, int database, String... options) {
        super(owner, host, port, password, database, options);
    }
}
