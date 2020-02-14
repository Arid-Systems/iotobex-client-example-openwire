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
 * JMSConnectionManager
 */

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import javax.jms.Connection;
import javax.jms.JMSException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import static com.aridsys.clientexampleopenwire.ClientExampleOpenWire.*;

public class JMSConnectionManager {

    private ActiveMQConnectionFactory connectionFactory = null;
    private PooledConnectionFactory pooledConnectionFactory = null;

    private Connection producerConnection;
    private Connection consumerConnection;

    public JMSConnectionManager()
    {
        try {
            System.out.println("Establishing producer and consumer connections");
            createConnectionFactory();
            producerConnection = pooledConnectionFactory.createConnection();
            consumerConnection = pooledConnectionFactory.createConnection();

            // Set client ID's for the connections. Want these to be unique so attach a random 10-character string.
            producerConnection.setClientID("con-id-prod-" + randomString(10));
            consumerConnection.setClientID("con-id-cons-" + randomString(10));

            // Start the connections.
            producerConnection.start();
            consumerConnection.start();
        }
        catch (JMSException exc) {
            exc.printStackTrace();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void createConnectionFactory() {
        // Create a connection factory.
        connectionFactory = createActiveMQConnectionFactory(ENDPOINT, USERNAME, PASSWORD);
        // Create a pooled connection factory.
        pooledConnectionFactory = createPooledConnectionFactory(connectionFactory);
    }

    private ActiveMQConnectionFactory createActiveMQConnectionFactory(String endpoint, String username, String password) {
        // Create a connection factory.
        final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(endpoint);
        // Pass the username and password.
        connectionFactory.setUserName(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    private static PooledConnectionFactory createPooledConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
        // Create a pooled connection factory.
        final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(connectionFactory);
        pooledConnectionFactory.setMaxConnections(10);
        return pooledConnectionFactory;
    }

    public Connection getProducerConnection() {
        return producerConnection;
    }

    public Connection getConsumerConnection() {
        return consumerConnection;
    }

    public void close() {
            // Clean up the producer and consumer.
            try {
                System.out.println("Closing JMS producer and consumer connections for ClientID: " + producerConnection.getClientID() + " and " + consumerConnection.getClientID());
                //consumer.close();
                producerConnection.close();
                consumerConnection.close();
            }
            catch (JMSException exc) {
                exc.printStackTrace();
            }
        }
    public static String randomString(int length)  {

        String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = ThreadLocalRandom.current();
        char[] buf = new char[length];
        char[] symbols = charSet.toCharArray();

        if (length < 1) throw new IllegalArgumentException();

        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];

        return new String(buf);
    }

}
