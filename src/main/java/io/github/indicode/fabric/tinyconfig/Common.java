package io.github.indicode.fabric.tinyconfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.SimpleMessage;

public class Common {
    public static final Logger LOGGER = LogManager.getLogger("TinyConfig", new MessageFactory() {
        @Override
        public Message newMessage(Object message) {
            return new SimpleMessage("[TinyConfig] " + message);
        }

        @Override
        public Message newMessage(String message) {
            return new SimpleMessage("[TinyConfig] " + message);
        }

        @Override
        public Message newMessage(String message, Object... params) {
            return new SimpleMessage("[TinyConfig] " + message);
        }
    });
}
