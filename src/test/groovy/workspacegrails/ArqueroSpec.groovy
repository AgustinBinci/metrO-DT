package workspacegrails

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import spock.lang.Specification
import grails.test.mixin.domain.DomainClassUnitTestMixin

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Arquero)
@TestMixin(DomainClassUnitTestMixin)
class ArqueroSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "testUnTest"() {
		when:
		Arquero unArquero = new Arquero()

		then:
		unArquero.getValorPorGolAfavor() == 5
    }
}
