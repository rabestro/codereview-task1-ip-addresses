package lv.id.jc.ipcounter.collector

import spock.lang.*

@Issue('1')
@Title('IPv4 Collector')
@Narrative('Integration test for IPv4 Collector')
@Subject([IPv4Collector, IPv4Converter, DoubleBitSetContainer])
class IPv4CollectorSpec extends Specification {

    def 'should count unique IPv4 addresses'() {
        given: 'IPv4 collector with standard container and converter'
        def underTest = IPv4Collector.countingUnique()

        when: 'we use collector to count unique ip addresses'
        def unique = ip.stream().collect(underTest)

        then: 'we get a correct number of unique addresses'
        unique == expected

        where:
        ip                                                       | expected
        []                                                       | 0
        ['0.0.0.0']                                              | 1
        ['1.0.1.0']                                              | 1
        ['0.0.0.0', '119.23.56.11', '43.111.0.12']               | 3
        ['1.2.3.4', '4.3.2.1', '1.2.3.4', '4.3.2.1']             | 2
        ['12.71.12.1', '12.71.12.2', '12.71.12.3', '12.71.12.4'] | 4
    }
}
