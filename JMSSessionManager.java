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
 * JMSSessionManager
 */

import javax.jms.*;
import static com.aridsys.clientexampleopenwire.ClientExampleOpenWire.jmsConnectionManager;

public class JMSSessionManager {

    private Connection producerConnection;
    private Connection consumerConnection;

    private Session producerSession;
    private Session consumerSession;

    public JMSSessionManager()
    {
        try {
            System.out.println("Creating producer and consumer sessions");
            producerConnection = jmsConnectionManager.getProducerConnection();
            consumerConnection = jmsConnectionManager.getConsumerConnection();
            producerSession = producerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            consumerSession = consumerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        }
        catch (JMSException exc) {
            exc.printStackTrace();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public Session getProducerSession() {
        return producerSession;
    }

    public Session getConsumerSession() {
        return consumerSession;
    }

    public void close() {
        // Clean up the producer and consumer.
        try {
            System.out.println("Closing JMS producer and consumer sessions for ClientID's: " + producerConnection.getClientID() + " and " + consumerConnection.getClientID());
            producerSession.close();
            consumerSession.close();
            // The producer and consumer connections are closed in JMSConnectionManager.close()
        }
        catch (JMSException exc) {
            exc.printStackTrace();
        }
    }
}
