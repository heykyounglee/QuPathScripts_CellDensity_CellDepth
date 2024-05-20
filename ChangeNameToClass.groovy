/**
 * This script change the name of each annotation to its class name.
 */ 
getAnnotationObjects().each { annotation ->
    className = annotation.getPathClass().toString();
    if (className[0] == 'R') {
        className = className.substring(7)
    } else if (className[0] == 'L') {
        className = className.substring(6)
    }
    annotation.setName(className)
}