package dev.magicmq.lettucewrap;

import io.lettuce.core.pubsub.RedisPubSubListener;

/**
 * A wrapper designed to simplify receiving messages from redis PubSub. Handling of asynchronous message receiving to synchronous method calling is done automatically. The {@link RedisListenerWrapper#messageReceived(String)} method is called synchronously.
 */
public abstract class RedisListenerWrapper<T> implements RedisPubSubListener<String, String> {

    private String channel;
    private T owner;

    /**
     *
     * @param channel The channel to listen for. Usually, this is a String unless if specified otherwise when the RedisListenerWrapped is constructed.
     */
    public RedisListenerWrapper(String channel) {
        this.channel = channel;
    }

    /**
     * This method should not be overridden or the functionality of the wrapped listener will be lost.
     * @see <a href="https://lettuce.io/core/release/api/">lettuce.io JavaDocs</a>
     */
    public abstract void message(String channel, String message);

    /**
     * Called when a message is received on the channel to which this listener belongs. This method will automatically be called synchronously, even though the message is received asynchronously.
     * @param message The contents of the message received
     */
    public abstract void messageReceived(String message);

    /**
     * @see <a href="https://lettuce.io/core/release/api/">lettuce.io JavaDocs</a>
     */
    @Override
    public void message(String pattern, String channel, String message) {
        //Should be empty
    }

    /**
     * @see <a href="https://lettuce.io/core/release/api/">lettuce.io JavaDocs</a>
     */
    @Override
    public void subscribed(String channel, long count) {
        //Should be empty
    }

    /**
     * @see <a href="https://lettuce.io/core/release/api/">lettuce.io JavaDocs</a>
     */
    @Override
    public void psubscribed(String pattern, long count) {
        //Should be empty
    }

    /**
     * @see <a href="https://lettuce.io/core/release/api/">lettuce.io JavaDocs</a>
     */
    @Override
    public void unsubscribed(String channel, long count) {
        //Should be empty
    }

    /**
     * @see <a href="https://lettuce.io/core/release/api/">lettuce.io JavaDocs</a>
     */
    @Override
    public void punsubscribed(String pattern, long count) {
        //Should be empty
    }

    /**
     * Get the owning plugin of this listener.
     * @return The owning plugin of this listener
     */
    public T getOwner() {
        return owner;
    }

    /**
     * For internal use only.
     * @param owner The owning plugin to which this listener should be set
     */
    protected void setOwner(T owner) {
        this.owner = owner;
    }

    /**
     * Get the channel on which this listner is listening.
     * @return The channel that this listener is listening on
     */
    public String getChannel() {
        return channel;
    }
}
