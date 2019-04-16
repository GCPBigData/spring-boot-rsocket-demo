package org.mvnsearch.rsocket.requester;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;

/**
 * rsocket configuration
 *
 * @author linux_china
 */
@Configuration
public class RSocketConfiguration {
    @Bean
    public RSocket rsocket() {
        return RSocketFactory.connect()
                .dataMimeType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                .transport(TcpClientTransport.create("localhost", 42252))
                .start()
                .block();
    }

    @Bean
    public RSocketRequester rsocketRequester(RSocket rSocket, RSocketStrategies strategies) {
        return RSocketRequester.create(rSocket, MimeTypeUtils.APPLICATION_JSON, strategies);
    }
}
