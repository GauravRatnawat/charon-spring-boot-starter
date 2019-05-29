package com.github.mkopylec.charon.test.specification

import static com.github.mkopylec.charon.test.assertions.Assertions.assertThat
import static com.github.mkopylec.charon.test.assertions.Assertions.assertThatServers
import static org.springframework.http.HttpMethod.GET
import static org.springframework.http.HttpStatus.OK

abstract class RequestProtocolHeadersRewritingBasicSpec extends BasicSpec {

    def "Should rewrite request protocol headers by default"() {
        when:
        def response = sendRequest(GET, '/default', ['TE': 'gzip'])

        then:
        assertThat(response)
                .hasStatus(OK)
                .hasNoBody()
        assertThatServers(localhost8080, localhost8081)
                .haveReceivedRequest(GET, '/default', ['Connection': 'close'])
    }

    def "Should not rewrite request protocol headers when proper interceptor is unset"() {
        when:
        def response = sendRequest(GET, '/request/protocol/headers', ['TE': 'gzip'])

        then:
        assertThat(response)
                .hasStatus(OK)
                .hasNoBody()
        assertThatServers(localhost8080, localhost8081)
                .haveReceivedRequest(GET, '/request/protocol/headers', ['TE': 'gzip'])
    }
}