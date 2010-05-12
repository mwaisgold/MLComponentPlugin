package ml.components

import grails.test.GroovyPagesTestCase
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException


class MLComponentTagLibTests extends GroovyPagesTestCase {

    void setUp(){
    	super.setUp()
        setControllerName "test"

    }

    def getComponent(){
        '<ml:component code="${code}" site="${site}" profile="${profile}" category="${category}"/>'
    }

    def componentString(code, site, profile, category){
        applyTemplate(getComponent(), [code: code, site: site, profile: profile, category:category])
    }

    void testSimpleComponentOK(){
        def comp = componentString("simpleComponent","MLA", "NEW", "MOT")
        println comp
        assert "El componente funcionas"
        assertEquals "El componente no trajo el contenido esperado", comp, "Simple component"
    }

    void testNoCodeFails(){
      try{
        def component = "<ml:component site='MLA'/>"
        applyTemplate(component)
        fail "No me debería dejar usar un componente sin el código"
      } catch (GrailsTagException e){
        assert "Llegue hasta acá OK"        
      }

    }

    void testDifferentParametersTemplateFails(){
      try{
          def component = "<ml:component code='simpleComponent' site='MLA' category='MOT' profile='NEW'/>"
          applyTemplate(component)

          def comp2 = "<ml:component code='simpleComponent' site='MLA' category='MOT' />"
          applyTemplate(comp2)

          fail "No debería poder cambiar la cantidad de parámetros"
      } catch (GrailsTagException e){
          assert "Llegue hasta acá OK"
      }

    }
}
