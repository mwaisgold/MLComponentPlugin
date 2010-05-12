package ml.components

import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException

/**
 * Created by IntelliJ IDEA.
 * User: mwaisgold
 * Date: 12/05/2010
 * Time: 13:21:00
 * To change this template use File | Settings | File Templates.
 */
class InvalidTemplateCallException extends GrailsTagException{

  InvalidTemplateCallException(message){
    super(message)
  }
  
}
