/* 
 * This script analyze all annotations in the image.
 */
import static qupath.lib.gui.scripting.QPEx.*
import qupath.lib.gui.measure.ObservableMeasurementTableData

selectAnnotations();
// Adjust cell detection settings here.
runPlugin('qupath.imagej.detect.cells.WatershedCellDetection', '{"detectionImage":"Channel 2","requestedPixelSizeMicrons":0.5,"backgroundRadiusMicrons":8.0,"backgroundByReconstruction":true,"medianRadiusMicrons":0.0,"sigmaMicrons":1.5,"minAreaMicrons":40,"maxAreaMicrons":400,"threshold":80.0,"watershedPostProcess":true,"cellExpansionMicrons":10.0,"includeNuclei":true,"smoothBoundaries":true,"makeMeasurements":true}')
def ob = new ObservableMeasurementTableData();
ob.setImageData( getCurrentImageData(),  getAnnotationObjects() );
// Change the measurements you want to get here.
// Note: due to unknown bugs, the density data is not necessarily correct or can be missing.
// It is good to calculate the density with Excel again after export.
getAnnotationObjects().each { 
    area = ob.getNumericValue(it, "Area µm^2") / 1000000
    num = ob.getNumericValue(it, "Num Detections") 
    it.getMeasurementList().putMeasurement("Density cell/mm^2", num / area)
}