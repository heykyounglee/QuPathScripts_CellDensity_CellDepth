/*
 * This script loads all regions from the ABBA registration for the image.
 */ 
clearAllObjects()
qupath.ext.biop.abba.AtlasTools.loadWarpedAtlasAnnotations(getCurrentImageData(), "acronym", false)