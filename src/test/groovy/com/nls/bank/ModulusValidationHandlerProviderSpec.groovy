package com.nls.bank

import com.nls.bank.handlers.*
import spock.lang.Specification
import spock.lang.Unroll

class ModulusValidationHandlerProviderSpec extends Specification {
    def "I can get the default instance provider"() {
        when:
        ModulusValidationHandlerProvider provider = ModulusValidationHandlerProvider.instance

        then:
        provider.defaultHandler.class == ModulusAndHandler
        provider.get(0) == null
        provider.get(1).class == Exception1Handler
        provider.get(2).class == Exception2Handler
        provider.get(3).class == Exception3Handler
        provider.get(4).class == Exception4Handler
        provider.get(5).class == Exception5Handler
        provider.get(6).class == Exception6Handler
        provider.get(7).class == Exception7Handler
        provider.get(8).class == Exception8Handler
        provider.get(9).class == Exception9Handler
        provider.get(10).class == Exception10Handler
        provider.get(11) == null
        provider.get(12).class == ModulusOrHandler
        provider.get(13).class == ModulusOrHandler
        provider.get(14).class == Exception14Handler
    }

    @Unroll("I can get I request a handler by rule the right handler is returned - #exception")
    def "I can get I request a handler by rule the right handler is returned"() {
        given:
        ModulusValidationHandlerProvider provider = ModulusValidationHandlerProvider.instance
        ModulusRule rule = new ModulusRule(
                new SortCodeRange(0, 999999),
                ModulusMethod.MOD10,
                new int[14],
                value == null ? Optional.empty() : Optional.<Integer> of(value))
        when:
        ModulusValidationHandler handler = provider.get(rule)

        then:
        handler.class == expected

        where:
        value | expected
        null  | ModulusAndHandler
        0     | ModulusAndHandler
        1     | Exception1Handler
        2     | Exception2Handler
        3     | Exception3Handler
        4     | Exception4Handler
        5     | Exception5Handler
        6     | Exception6Handler
        7     | Exception7Handler
        8     | Exception8Handler
        9     | Exception9Handler
        10    | Exception10Handler
        11    | ModulusAndHandler
        12    | ModulusOrHandler
        13    | ModulusOrHandler
        14    | Exception14Handler
    }

}
