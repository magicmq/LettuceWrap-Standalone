package dev.magicmq.lettucewrap.exception;

public class ChannelAlreadyRegisteredException extends RuntimeException {

    private static final long serialVersionUID = 3247858659205807929L;

    public ChannelAlreadyRegisteredException(String message) {
        super(message);
    }
}
