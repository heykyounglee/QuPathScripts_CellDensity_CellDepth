/**
 * This script includes “Left” and “Right” in the class names for the two annotations of each ROI.
 */ 
// Note: before running this script, please make sure there are exactly two annotations for 
// under each class name, one on the left, and the other on the right
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