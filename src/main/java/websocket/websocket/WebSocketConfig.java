package websocket.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.PathMatcher;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private Message<Client_Gui> message_gui;
    private Message<Client> message_client;
    private MessageHeaders messageHeaders;
    private MessageHeaders messageHeaders2;

    private MessageChannel messageChannel;
    private MessageChannel messageChannel2;

    private ChannelInterceptor channelInterceptor;

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private PathMatcher pathMatcher;


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        config.setApplicationDestinationPrefixes("/logingui");
        config.enableStompBrokerRelay("/clientgui");
        config.enableSimpleBroker("/clientgui");
        config.setCacheLimit(400);
        config.setUserDestinationPrefix("/logingui");
        config.setApplicationDestinationPrefixes("/logingui");
        config.configureBrokerChannel().interceptors(getChannelInterceptor());
        config.configureBrokerChannel().taskExecutor(getThreadPoolTaskExecutor());
        config.setPathMatcher(getPatchMatcher());
        config.setUserRegistryOrder(1);
    }



    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/clientgui");
        registry.addEndpoint("/client");
        registry.addEndpoint("/client{id}");


    }



    private Message<Client_Gui> getMessage()
    {
        Message<Client_Gui> clientGuiMessage = new Message<Client_Gui>() {

            @Override
            public Client_Gui getPayload() {
                return new Client_Gui();

            }


            @Override
            public MessageHeaders getHeaders() {
                Map<String,Object> headerMap = new HashMap<>();
                headerMap.put(MessageHeaders.REPLY_CHANNEL,"http://locahost:8080/clientgui");
                headerMap.put(MessageHeaders.CONTENT_TYPE, MediaType.ALL);
                headerMap.put(MessageHeaders.ID, 1);
                headerMap.put(MessageHeaders.TIMESTAMP, Timestamp.valueOf(LocalDateTime.now()));


                messageHeaders = new MessageHeaders(headerMap);

                if(messageHeaders.isEmpty())
                {
                    Logger log = LoggerFactory.getLogger(WebSocketConfig.class);
                    log.error("CHANNEL IN MESSAGE_HEADERS IS NOT FOUND");
                    messageHeaders.put(MessageHeaders.ERROR_CHANNEL,"ERROR CHANNEL");
                }
                else
                {
                    try {
                        URL url = new URL("http","localhost",8080,"clientgui");
                        URLConnection open = url.openConnection();
                        open.connect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }



                return messageHeaders;
            }
        };

        return message_gui;
    }


    private Message<LoginGui> getMessage2()
    {
        Message<LoginGui> clientGuiMessage2 = new Message<LoginGui>() {


            @Override
            public LoginGui getPayload() {
                return new LoginGui();

            }



            public MessageHeaders getHeaders() {
                Map<String,Object> headersMap = new HashMap<>();
                headersMap.put(MessageHeaders.REPLY_CHANNEL,"http://locahost:8080/clientgui");
                headersMap.put(MessageHeaders.CONTENT_TYPE, MediaType.ALL);
                headersMap.put(MessageHeaders.ID, 1);
                headersMap.put(MessageHeaders.TIMESTAMP, Timestamp.valueOf(LocalDateTime.now()));


                messageHeaders2 = new MessageHeaders(headersMap);

                if(messageHeaders2.isEmpty())
                {
                    Logger log = LoggerFactory.getLogger(WebSocketConfig.class);
                    log.error("CHANNEL IN MESSAGE_HEADERS IS NOT FOUND");
                    messageHeaders2.put(MessageHeaders.ERROR_CHANNEL,"ERROR CHANNEL");
                }
                else
                {
                    try {
                        URL url = new URL("http","localhost",8080,"message");
                        URLConnection open = url.openConnection();
                        open.connect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }



                return messageHeaders2;
            }
        };

        return clientGuiMessage2;
    }





    private MessageChannel sendMessageChannel()
    {
        messageChannel.send(getMessage());
        return messageChannel;
    }

    private MessageChannel sendMessageChannel2()
    {
        messageChannel2.send(getMessage2());
        return messageChannel2;
    }


    private ChannelInterceptor getChannelInterceptor() {

        channelInterceptor.postSend(getMessage(),sendMessageChannel(),true);
        channelInterceptor.preSend(getMessage(),sendMessageChannel());
        channelInterceptor.afterSendCompletion(getMessage(),sendMessageChannel(),false,new MessagingException("ERROR SEND MESSAGE "));


        channelInterceptor.afterReceiveCompletion(getMessage2(),sendMessageChannel2(),new MessagingException("ERROR RECEIVE MESSAGE"));
        channelInterceptor.preReceive(sendMessageChannel2());
        channelInterceptor.postReceive(getMessage2(),sendMessageChannel2());
        return channelInterceptor;
    }

    private ThreadPoolTaskExecutor getThreadPoolTaskExecutor()
    {
        ThreadPoolTaskExecutor thread = new ThreadPoolTaskExecutor();
        thread.setCorePoolSize(5000);
        thread.setKeepAliveSeconds(4000);
        thread.setMaxPoolSize(5000);
        thread.setAwaitTerminationSeconds(30);
        thread.setQueueCapacity(500);
        thread.setThreadPriority(1);
        thread.setWaitForTasksToCompleteOnShutdown(true);

        return threadPoolTaskExecutor;
    }

    private PathMatcher getPatchMatcher() {
        return pathMatcher;


    }


}




