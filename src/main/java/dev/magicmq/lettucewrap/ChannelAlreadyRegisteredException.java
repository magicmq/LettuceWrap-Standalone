package dev.magicmq.lettucewrap;

public class ChannelAlreadyRegisteredException extends RuntimeException {

    public ChannelAlreadyRegisteredException(String message) {
        super(message);
    }
}
