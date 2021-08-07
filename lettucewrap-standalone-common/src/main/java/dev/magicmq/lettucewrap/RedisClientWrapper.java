package dev.magicmq.lettucewrap;

import dev.magicmq.lettucewrap.exception.ChannelAlreadyRegisteredException;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * A wrapper class that wraps lettuce's RedisClient class.
 */
public class RedisClientWrapper<T> {

    private T owner;

    private RedisClient client;
    private StatefulRedisPubSubConnection<String, String> connectionIncoming;
    private StatefulRedisPubSubConnection<String, String> connectionOutgoing;
    private HashMap<String, RedisListenerWrapper> listeners;

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     */
    public RedisClientWrapper(T owner, String host) {
        this(owner, host, 6379, null, 0, (String[]) null);
    }

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     * @param port The port of the redis server to connect to, default is 6379
     */
    public RedisClientWrapper(T owner, String host, int port) {
        this(owner, host, port, null, 0, (String[]) null);
    }

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     * @param password The password of the redis server, or null if there is no password
     */
    public RedisClientWrapper(T owner, String host, String password) {
        this(owner, host, 6379, password, 0, (String[]) null);
    }

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     * @param port The port of the redis server to connect to, default is 6379
     * @param password The password of the redis server, or null if there is no password
     */
    public RedisClientWrapper(T owner, String host, int port, String password) {
        this(owner, host, port, password, 0, (String[]) null);
    }

    /**
     * Initialize a new redis client wrapper.
     * @param owner The plugin creating this client. This should be the plugin's main class
     * @param host The hostname/IP of the redis server to connect to
     * @param port The port of the redis server to connect to, default is 6379
     * @param password The password of the redis server, or null if there is no password
     * @param database The database that should be connected to on the redis server, default is 0
     */
    public RedisClientWrapper(T owner, String host, int port, String password, int database) {
        this(owner, host, port, password, database, (String[]) null);
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
    public RedisClientWrapper(T owner, String host, int port, String password, int database, String... options) {
        if (owner == null)
            throw new IllegalArgumentException("Owner cannot be null!");
        if (host == null || host.isEmpty())
            throw new IllegalArgumentException("Host cannot be null or an empty string!");

        this.owner = owner;

        StringBuilder redisURL = new StringBuilder();
        redisURL.append("redis://");
        if (password != null) {
            try {
                redisURL.append(URLEncoder.encode(password, "UTF-8"));
                redisURL.append("@");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return;
            }
        }
        redisURL.append(host);
        if (port != 6379) {
            redisURL.append(":");
            redisURL.append(port);
        }
        if (database != 0) {
            redisURL.append("/");
            redisURL.append(database);
        }
        if (options != null) {
            redisURL.append("?");
            for (int i = 0; i < options.length; i++) {
                redisURL.append(options[i]);
                if (i < (options.length - 1))
                    redisURL.append("&");
            }
        }

        String url = redisURL.toString();
        this.client = RedisClient.create(url);
        this.client.setOptions(ClientOptions.builder()
                .autoReconnect(true)
                .pingBeforeActivateConnection(true)
                .build());
        connectionIncoming = client.connectPubSub();
        connectionOutgoing = client.connectPubSub();
        listeners = new HashMap<>();
    }

    /**
     * Used to close this client when finished. For example, if the plugin unloads, then this should be called.
     * <b>NOTE:</b> This is not closed automatically! You <b>must</b> close this wrapper unless if the server is shutting down.
     */
    public void close() {
        connectionIncoming.close();
        connectionOutgoing.close();
    }

    /**
     * Get all registered listener.
     * @return A mutable HashMap containing a HashMap of registered listeners, where keys are the channel name and values are the listeners themselves.
     */
    public HashMap<String, RedisListenerWrapper> getListeners() {
        return listeners;
    }

    /**
     * Used to register a new listener with this RedisClientWrapper.
     * @param listener The RedisListenerWrapper to register. This will be called when a message is received from the channel.
     */
    public void addListener(RedisListenerWrapper listener) {
        if (listeners.get(listener.getChannel()) != null)
            throw new ChannelAlreadyRegisteredException("The channel " + listener.getChannel() + " already has a registered listener!");

        listener.setOwner(owner);
        listeners.put(listener.getChannel(), listener);

        connectionIncoming.addListener(listener);
        connectionIncoming.sync().subscribe(listener.getChannel());
    }

    /**
     * Deregisters and removes a listener from this client.
     * @param channel The channel of the listener that should be removed
     * @return True if a listener associated with the given channel was removed, false if no listener was found under the specified channel
     */
    public boolean removeListener(String channel) {
        RedisListenerWrapper toReturn = listeners.remove(channel);
        if (toReturn != null) {
            connectionIncoming.removeListener(toReturn);
            connectionIncoming.sync().unsubscribe(toReturn.getChannel());
            return true;
        }
        return false;
    }

    /**
     * Deregisters and removes a listener from this client.
     * @param listener The listener that should be removed
     * @return True if the listener was removed, false if the listener was not registered to begin with
     */
    public boolean removeListener(RedisListenerWrapper listener) {
        RedisListenerWrapper toReturn = listeners.remove(listener.getChannel());
        if (toReturn != null) {
            connectionIncoming.removeListener(toReturn);
            connectionIncoming.sync().unsubscribe(toReturn.getChannel());
            return true;
        }
        return false;
    }

    /**
     * Publish a message to the given channel through this client.
     * @param channel The channel to which the message should be written
     * @param message The content of the message to publish
     */
    public void sendMessage(String channel, String message) {
        connectionOutgoing.async().publish(channel, message);
    }
}
