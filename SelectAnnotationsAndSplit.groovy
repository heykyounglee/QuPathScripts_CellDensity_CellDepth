/*
 * This script serves as an alternative for situations where
 * SelectLeftAnnotations/SelectRightAnnotations/SelectAllAnnotations doesn't work.
 * It imports each ROI as one annotation, splits the annotation into left and right halves,
 * and deletes annotations that are too small.
 */
clearAllObjects()
// Load all ROIs (combine left and right)
qupath.ext.biop.abba.AtlasTools.loadWarpedAtlasAnnotations(getCurrentImageData(), "acronym", false)
// Change the ROIs here to the ROIs in your project
def ROIClasses = [
    getPathClass("RSPv"),
    getPathClass("RSPd"), 
    getPathClass("RSPagl"),
    getPathClass("AUDp"),  
    getPathClass("AUDd"),  
    getPathClass("AUDv"),    
    getPathClass("VISp"),
    getPathClass("VISal"),
    getPathClass("VISl"),
    getPathClass("VISrl"), 
    getPathClass("VISa"),
    getPathClass("VISam"), 
    getPathClass("VISpm"),
    getPathClass("SSp-tr"),   
    getPathClass("SSp-ll"),    
    getPathClass("SSp-ul"),
    getPathClass("SSp-un"),  
    getPathClass("SSp-bfd"),  
    getPathClass("SSp-n"),
    getPathClass("SSs"),
    ]
// Remove all ROIs except the ones listed above
def annotationsToDiscard = getAnnotationObjects().findAll{!(ROIClasses.contains(it.getPathClass()))}
removeObjects(annotationsToDiscard, true)

// split annotations into left and right
selectAnnotations();
runPlugin('qupath.lib.plugins.objects.SplitAnnotationsPlugin', '{}')

// filter out annotations that are too small
def annotationsTooSmall = getAnnotationObjects().findAll{it.getROI().getArea() <= 3000}
removeObjects(annotationsTooSmall, true)
