/*
 * This script separates each point you drew with the point tool a separate annotation.
 */
selectAnnotations()
runPlugin('qupath.lib.plugins.objects.SplitAnnotationsPlugin', '{}')