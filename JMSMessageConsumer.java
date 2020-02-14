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
 * JMSMessageConsumer
 */

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import static com.aridsys.clientexampleopenwire.ClientExampleOpenWire.jmsSessionManager;

public class JMSMessageConsumer {

    private MessageConsumer consumer;
    private String listenerTopic;

    public JMSMessageConsumer(String topic) {

        Session consumerSession;
        Destination consumerDestination;

        listenerTopic = topic;
        try {
            System.out.println("Creating a message consumer");
            consumerSession = jmsSessionManager.getConsumerSession();
            consumerDestination = consumerSession.createTopic(listenerTopic);
            consumer = consumerSession.createConsumer(consumerDestination);
            consumer.setMessageListener(new JMSMessageListener(listenerTopic));

        } catch (Exception exc) {
            exc.printStackTrace();
        }
}

    public void close() {
        // Clean up the consumer.
        try {
            System.out.println("Closing consumer for topic: " + listenerTopic);
            consumer.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
