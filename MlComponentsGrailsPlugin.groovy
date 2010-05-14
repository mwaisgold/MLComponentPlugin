class MlComponentsGrailsPlugin {
    // the plugin version
    def version = "1.0-Beta"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.2.2 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def author = "Matías Waisgold"
    def authorEmail = "mwaisgold@gmail.com"
    def title = "Plugin para poder controlar los componentes web en ML"
    def description = '''\\
El pluggin parte de la idea de replicar los componentes en todas sus posibilidades posibles.
La idea es tener para cada proyecto una buena configuración. Por ejemplo, para SYI la idea es 
replicar los componentes por site, categ y perfil. Lo que va a llevar una estructura de directorios
grails-app/views/CONTROLLER_NAME/components/COMPONENT_NAME/_SITE_CATEG_PREFIL.gsp (para poder utilizar los renders)
Por convención cada uno de estos campos deberá tener si o sí 3 letras y no se podrá repetir los posibles valores entre uno y otro.
Por ejemplo: NEW no puede ser categoría y perfil. Si pertence a un grupo, no puede pertenecer al otro.
Por defecto, si no encuentra la configuración va a tomar el template llamado: _ALL_ALL_ALL.gsp dentro del componente.
'''

    // URL to the plugin's documentation
    def documentation = "http://mercadowiki.ml.com:8080/plugin/ml-components" 

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before 
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
