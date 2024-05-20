/*
 * This script loads all regions from the ABBA registration for the image 
 * and name the regions by their full names.
 */ 
clearAllObjects()
qupath.ext.biop.abba.AtlasTools.loadWarpedAtlasAnnotations(getCurrentImageData(), "name", true)