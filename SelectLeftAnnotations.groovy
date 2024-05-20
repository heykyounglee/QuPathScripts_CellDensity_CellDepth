/*
 * This script imports all ROIs on the right hemisphere.
 */ 
import static qupath.lib.gui.scripting.QPEx.*
import qupath.lib.gui.measure.ObservableMeasurementTableData
// Load all ROIs (split left and right)
qupath.ext.biop.abba.AtlasTools.loadWarpedAtlasAnnotations(getCurrentImageData(), "acronym", true)
def annotations = getAnnotationObjects()
// Change the ROIs here to the ROIs in your project
def ROIClasses = [ 
    getPathClass("Left: RSPv"),
    getPathClass("Left: RSPd"),   
    getPathClass("Left: RSPagl"),   
    getPathClass("Left: AUDp"), 
    getPathClass("Left: AUDd"), 
    getPathClass("Left: AUDv"),
    getPathClass("Left: VISp"),  
    getPathClass("Left: VISal"),  
    getPathClass("Left: VISl"),
    getPathClass("Left: VISrl"),
    getPathClass("Left: VISa"),
    getPathClass("Left: VISam"),    
    getPathClass("Left: VISpm"),
    getPathClass("Left: SSp-tr"),
    getPathClass("Left: SSp-ll"), 
    getPathClass("Left: SSp-ul"),
    getPathClass("Left: SSp-un"),
    getPathClass("Left: SSp-bfd"),
    getPathClass("Left: SSp-n"),
    getPathClass("Left: SSs"),
    ]
// Remove all ROIs except the ones listed above
def annotationsToDiscard = annotations.findAll{!(ROIClasses.contains(it.getPathClass()))}
removeObjects(annotationsToDiscard, true)
