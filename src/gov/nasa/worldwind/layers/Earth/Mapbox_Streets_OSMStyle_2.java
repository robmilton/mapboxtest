/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package gov.nasa.worldwind.layers.Earth;

import gov.nasa.worldwind.layers.mercator.*;

import java.net.*;

/**
 * @version $Id: OSMMapnikLayer.java 1171 2013-02-11 21:45:02Z dcollins $
 */
public class Mapbox_Streets_OSMStyle_2 extends BasicMercatorTiledImageLayer
{
    public Mapbox_Streets_OSMStyle_2()
    {
        super("Mapbox_Streets_OSMStyle_2", "Earth/Mapbox_Streets_OSMStyle_2", 19, 256, false, ".png", new URLBuilder());
    }

    private static class URLBuilder extends MercatorTileUrlBuilder
    {
        private String accessToken = "pk.eyJ1IjoiYWlycG9ydG1hc3RlciIsImEiOiJjand4azM3M2MwMXMzNGFxcjZwMXR5MjJqIn0.rtQ97ju2aDx_TZ_1rl-o7g"; // temp token for testing

        @Override
        protected URL getMercatorURL(int x, int y, int z) throws MalformedURLException
        {
            return new URL("https://api.mapbox.com/styles/v1/airportmaster/cirzc39ca000ig9m1mg6vmx1c/tiles/256/" + z + "/" + x + "/" + y + "?access_token=" + accessToken);
        }
    }

    @Override
    public String toString()
    {
        return "Mapbox_Streets_OSMStyle_2";
    }
}
