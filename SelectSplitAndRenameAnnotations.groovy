/*
 * This script is simply a concactenation of SelectAnnotationsAndSplit and SpecifyLeftRight.
 * It is useful only if none of your ROIs have its left and right halves connected.
 */
import static qupath.lib.gui.scripting.QPEx.*
import qupath.lib.gui.measure.ObservableMeasurementTableData

def hierarchy = getCurrentHierarchy()
// Load all ROIs (combine left and right)
clearAllObjects()
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

// rename the classes of annotations to include "Left: " and "Right: "
for (ROIClass in ROIClasses) {
    annotationsToRename = getAnnotationObjects().findAll{it.getPathClass().equals(ROIClass)}
    if (annotationsToRename) {
        annotationsToRename.sort{it.getROI().getCentroidX()}
        left = annotationsToRename[0]
        right = annotationsToRename[1]
        left.setPathClass(getPathClass("Right: " + left.getPathClass().toString()))
        right.setPathClass(getPathClass("Left: " + right.getPathClass().toString()))
    }
}