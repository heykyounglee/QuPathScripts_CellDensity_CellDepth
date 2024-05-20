/*
 * This script removes all annotations whose class starts with "Right". 
 * (i.e., all annotations on the left hemisphere)
 */
RightAnnotations = getAnnotationObjects().findAll {
    it.getPathClass().toString().substring(0, 5).equals("Right")
}
removeObjects(RightAnnotations, true)