/*
 * This script removes all annotations whose class starts with "Left". 
 * (i.e., all annotations on the right hemisphere)
 */
LeftAnnotations = getAnnotationObjects().findAll {
    it.getPathClass().toString().substring(0, 4).equals("Left")
}
removeObjects(LeftAnnotations, true)