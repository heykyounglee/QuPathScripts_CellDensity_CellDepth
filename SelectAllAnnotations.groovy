/*
 * This script imports all ROIs on both hemispheres.
 */ 
import static qupath.lib.gui.scripting.QPEx.*
import qupath.lib.gui.measure.ObservableMeasurementTableData
// Load all ROIs (split left and right)
qupath.ext.biop.abba.AtlasTools.loadWarpedAtlasAnnotations(getCurrentImageData(), "acronym", true)
// Change the ROIs here to the ROIs in your project
def annotations = getAnnotationObjects()
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
    getPathClass("Right: RSPv"),
    getPathClass("Right: RSPd"),   
    getPathClass("Right: RSPagl"),   
    getPathClass("Right: AUDp"), 
    getPathClass("Right: AUDd"), 
    getPathClass("Right: AUDv"),
    getPathClass("Right: VISp"),  
    getPathClass("Right: VISal"),  
    getPathClass("Right: VISl"),
    getPathClass("Right: VISrl"),
    getPathClass("Right: VISa"),
    getPathClass("Right: VISam"),    
    getPathClass("Right: VISpm"),
    getPathClass("Right: SSp-tr"),
    getPathClass("Right: SSp-ll"), 
    getPathClass("Right: SSp-ul"),
    getPathClass("Right: SSp-un"),
    getPathClass("Right: SSp-bfd"),
    getPathClass("Right: SSp-n"),
    getPathClass("Right: SSs"),
    ]
// Remove all ROIs except the ones listed above
def annotationsToDiscard = annotations.findAll{!(ROIClasses.contains(it.getPathClass()))}
removeObjects(annotationsToDiscard, true)