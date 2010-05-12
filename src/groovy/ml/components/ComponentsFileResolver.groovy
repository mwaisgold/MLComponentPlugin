package ml.components

/**
 * Created by IntelliJ IDEA.
 * User: mwaisgold
 * Date: 12/05/2010
 * Time: 12:14:01
 * To change this template use File | Settings | File Templates.
 */
class ComponentsFileResolver {

  static BASE_COMPONENT_DIR = "components"
  static BASE_VIEWS_DIR = "grails-app/views"
  static DEFAULT_CONFIG_LENGTH = 3

  def config
  def defaultTemplate
  def evalRange

  ComponentsFileResolver(config){
    this.config = config
    this.defaultTemplate = explodeParametersList(config, "_"){
      "ALL"
    }
    def topRange = config.size() * DEFAULT_CONFIG_LENGTH + config.size() - 1
    this.evalRange = 1..topRange
  }
  
  private def explodeParametersList(parameters, separator, Closure eachCall){
    def returnString = ""
    parameters.eachWithIndex { val, i ->
      if (i > 0){
        returnString += separator
      }
      returnString += eachCall(val)
    }
    returnString
  }

  def resolveComponentDir(String controller, String componentName, parameters = []){
        if ( parameters.size() != config.size()){
          throw new InvalidTemplateCallException("No se está llamando correctamente al componente")
        }
		String templateName = "${BASE_COMPONENT_DIR}/${componentName}/"
		String dirName = "${BASE_VIEWS_DIR}/${controller}/${templateName}"
		List<RankedFile> probableFiles = new ArrayList<RankedFile>();

		File dirComponent = new File(dirName)
        def componentMatch = explodeParametersList(parameters, "_"){
          "(ALL|${it})"
        }
        println componentMatch
        def componentMatchRank = explodeParametersList(parameters,"|"){
          it
        }
        println componentMatchRank
		dirComponent.eachFile {
			def template = it.name[evalRange]
			if ( template =~ /${componentMatch}/){              

				def match = template =~ /${componentMatchRank}/
				def rank = match.count

				if (rank > 0){
					probableFiles << new RankedFile(tplName: template, rank: rank)
				}
			}
		}

		probableFiles.sort { o1,o2 ->
			o2.getRank().compareTo o1.getRank()
		}

		if (probableFiles.size() == 0) {
			//Si no hay nada, devuelvo el all
			return "${templateName}${defaultTemplate}"
		} else if (probableFiles.size() == 1){
			return getFirstFile(templateName, probableFiles)
		} else {
			if (probableFiles[0].getRank() == probableFiles[1].getRank()){
				throw new AmbiguousTemplateDefinitionException("No se puede encontrar template para [${parameters}]");
			} else {
				return getFirstFile(templateName, probableFiles)
			}
		}
	}

	private static getFirstFile(templateName, probableFiles){
		def firstFile = probableFiles[0]
		return "${templateName}${firstFile.getTplName()}"
	}

}
