package lv.id.jc.ipcounter.collector

import spock.lang.Ignore
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Issue('1')
@Title('Unique Address Counter')
@Narrative('Integration test for IPv4 addresses counter')
//@Subject([SimpleContainer, SimpleConverter, SimpleCounter])
class SimpleCounterSpec extends Specification {
//    def container = new SimpleContainer()
//    def converter = new SimpleConverter()

    @Ignore
    def 'should count unique IPv4 addresses'() {
        given: 'counter with real container and converter'
//        def counter = new SimpleCounter(container, converter)

        when: 'we use counter to calculate unique ip addresses'
        def unique = counter.applyAsLong ip.stream()

        then: 'we get a correct count of unique addresses'
        unique == expected

        where:
        ip                                           | expected
        []                                           | 0
        ['0.0.0.0']                                  | 1
        ['1.0.1.0']                                  | 1
        ['0.0.0.0', '119.23.56.11', '43.111.0.12']   | 3
        ['1.2.3.4', '4.3.2.1', '1.2.3.4', '4.3.2.1'] | 2
    }
}