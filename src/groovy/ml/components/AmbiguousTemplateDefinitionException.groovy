package ml.components

import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
/**
 * Created by IntelliJ IDEA.
 * User: mwaisgold
 * Date: 12/05/2010
 * Time: 12:17:19
 * To change this template use File | Settings | File Templates.
 */

class AmbiguousTemplateDefinitionException extends GrailsTagException {

	AmbiguousTemplateDefinitionException(String msg){
		super(msg)
	}
}
