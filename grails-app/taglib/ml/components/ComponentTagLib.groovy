package ml.components

import org.springframework.web.context.request.RequestContextHolder

class ComponentTagLib {
  static namespace = "ml"
  private def componentResolver

  private def parseAttributes(attrs){
    def keys = attrs.keySet()
    keys.remove("code")
    keys
  }

  private def getNewComponentResolver(attrs){
    def config = parseAttributes(attrs)
    new ComponentsFileResolver(config)
  }

  private def getComponentResolver(attrs){
    componentResolver ?: getNewComponentResolver(attrs)
  }

  private def parseValues(attrs){
    def keys = parseAttributes(attrs)
    def values = []
    keys.each {
      values << attrs[it]
    }
    values
  }

  def component = { attrs, body ->
     def code = attrs["code"]
     if (!code){
       throw new InvalidTemplateCallException("No se puede llamar a un template sin un atributo 'code'")
     }
     def resolver = getComponentResolver(attrs)
     def controller = RequestContextHolder.currentRequestAttributes().controllerName
     def template = resolver.resolveComponentDir(controller, code, parseValues(attrs))
     out << render(template: template)

  }
}
