package com.aridsys.clientexampleopenwire;

/*
 * Copyright 2020 Arid Systems Pty Ltd.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

/*
 * JMSMessageListener
 *
 * Callback for received message listener.
 */

import javax.jms.*;

public class JMSMessageListener implements javax.jms.MessageListener {

    private String listenerTopic;

    public JMSMessageListener(String listenerTopic) {
        // Note - 'listenerTopic' passed as informative only (for console print).
        System.out.println("Setting up a listener for incoming messages");
        this.listenerTopic = listenerTopic;
    }

    public void onMessage(Message message) {

        String fullMessageText = "";
        String receivedMessageText = "(not displaying)";
        String messageType = "Unknown";

        // Determine message type and print to console as appropriate. The message published
        // in this example is of type TextMessage
        try {
            if (message instanceof TextMessage) {
                // TextMessage - JMS publishes this format so published message should be caught.
                messageType = "Text (as expected)";
                TextMessage textMessage = (TextMessage) message;
                receivedMessageText = textMessage.getText();
            } else if (message instanceof BytesMessage) {
                // Message type Bytes (which is binary). MQTT publishes to this format.
                messageType =  "Bytes (possibly MQTT)";
                BytesMessage bytesMessage = (BytesMessage) message;
                byte[] data = new byte[(int) bytesMessage.getBodyLength()];
                bytesMessage.readBytes(data);
                fullMessageText = new String(data);
                receivedMessageText = fullMessageText;
            } else if (message instanceof StreamMessage)
                messageType = "Stream";
            else if (message instanceof MapMessage)
                messageType = "Map";
            else if (message instanceof ObjectMessage)
                messageType = "Object";

            System.out.println("Received message of type " + messageType + " for listenerTopic: " + listenerTopic + " - Message: " + receivedMessageText);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
