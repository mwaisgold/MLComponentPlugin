package ml.components

import ml.components.ComponentsFileResolver
import grails.test.GrailsUnitTestCase
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException

class ComponentTests extends GrailsUnitTestCase {
    def resolver
    protected void setUp() {
        super.setUp()

        resolver = new ComponentsFileResolver(["SITE","CATEG","PROFILE"])
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testResolverInstanciatesOK() {      
        assert true
    }

    void testBadCallForComponentParams(){
      try{
        resolver.resolveComponentDir("test","simpleComponent",["MLA","STD"])
        fail "No debería llegar hasta acá"
      } catch (GrailsTagException e){
        assert "Tiro la excepción ok"
      }
    }

    void testResolveComponentNameOK(){
      def result = resolver.resolveComponentDir("test","simpleComponent",["MLA","STD","NEW"])
      println result
      assertEquals "El componente no es el correcto", result, "components/simpleComponent/ALL_ALL_ALL"
    }

    void testResolveComponentDuplicatedBySiteOK(){
      def result = resolver.resolveComponentDir("test","replicatedComponentSite",["MLA","STD","NEW"])
      println result
      assertEquals "El componente no es el correcto para MLA", result, "components/replicatedComponentSite/MLA_ALL_ALL"

      def resultMLB = resolver.resolveComponentDir("test","replicatedComponentSite",["MLB","STD","NEW"])
      println resultMLB
      assertEquals "El componente no es el correcto para MLB", resultMLB, "components/replicatedComponentSite/MLB_ALL_ALL"
    }

    void testResolveComponentDuplicatedBySiteAndConfigOK(){
      def result = resolver.resolveComponentDir("test","replicatedComponentSiteConfig",["MLA","STD","NEW"])
      println result
      assertEquals "El componente no es el correcto para MLA", result, "components/replicatedComponentSiteConfig/MLA_ALL_ALL"

      def resultMOT = resolver.resolveComponentDir("test","replicatedComponentSiteConfig",["MLA","MOT","NEW"])
      println resultMOT
      assertEquals "El componente no es el correcto para MLB", resultMOT, "components/replicatedComponentSiteConfig/MLA_MOT_ALL"
    }

    void testResolveAmbiguousDefinitionConfig(){
      try{
        def result = resolver.resolveComponentDir("test","inconclusiveComponent",["MLA","MOT","NEW"])
        fail "No debería llegar hasta acá"
      } catch (GrailsTagException e){
        assert "Tiró el error esperado"
      }
    }

    void testResolveFullComponentDefinitionOK(){
      def result = resolver.resolveComponentDir("test","fullComponent",["MLA","MOT","NEW"])
      println result
      assertEquals  "El componente no es el esperado", result, "components/fullComponent/MLA_MOT_NEW"
    }
}
