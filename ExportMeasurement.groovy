/*
 * This script exports cell count & density measurements for all images in the project.
 */
import qupath.lib.gui.tools.MeasurementExporter
import qupath.lib.objects.PathAnnotationObject

// Get the list of all images in the current project
def project = getProject()
def imagesToExport = project.getImageList()

// Separate each item in the output file with the separator you want
def separator = ","

// Choose the columns that will be included in the export
// Note: if 'columnsToInclude' is empty, all columns will be included
def columnsToInclude = new String[]{"Image", "Class", "Area µm^2", "Num Detections", "Density cell/mm^2"}

// Choose the type of objects that the export will process
// Other possibilities include:
//    1. PathDetectionObject
//    2. PathRootObject
// Note: import statements should then be modified accordingly
def exportType = PathAnnotationObject.class

// Choose your *full* output path
def projectPath = project.getPath().toString()
def projectName = projectPath.substring(projectPath.indexOf('/', 51)+1, projectPath.length() - 15)
def outputPath = "/Users/owensong/Desktop/Lee Lab/Results/Yanis/" + projectName + "_result.tsv"
def outputFile = new File(outputPath)

// Create the measurementExporter and start the export
def exporter  = new MeasurementExporter()
                  .imageList(imagesToExport)            // Images from which measurements will be exported
                  .separator(separator)                 // Character that separates values
                  .includeOnlyColumns(columnsToInclude) // Columns are case-sensitive
                  .exportType(exportType)               // Type of objects to export
                  .exportMeasurements(outputFile)        // Start the export process

