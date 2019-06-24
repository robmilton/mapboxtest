package gov.nasa.worldwind.layers.Earth;

import gov.nasa.worldwind.layers.mercator.Mapbox_BasicMercatorTiledImageLayer;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.avlist.AVListImpl;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.layers.mercator.MercatorSector;
import gov.nasa.worldwind.util.LevelSet;
import gov.nasa.worldwind.util.Tile;
import gov.nasa.worldwind.util.TileUrlBuilder;
import java.net.MalformedURLException;
import java.net.URL;

public class Mapbox_Black extends Mapbox_BasicMercatorTiledImageLayer {
    static String strName = "Mapbox_Black";
    
    public Mapbox_Black() {
        super(makeLevels());
        this.splitScale = 1.4;
    }

    private static LevelSet makeLevels() {
        AVList params = new AVListImpl();

        int intTileWidth = 256;
        int intTileHeight = 256;
        int intNumLevels = 20;

        params.setValue(AVKey.TILE_WIDTH, intTileWidth);
        params.setValue(AVKey.TILE_HEIGHT, intTileHeight);
        params.setValue(AVKey.DATA_CACHE_NAME, "Earth/" + strName);
        params.setValue(AVKey.SERVICE, "https://api.mapbox.com/styles/v1/airportmaster/cjhjerqz72m0e2smwjmlzm5sm/tiles/256/");
        params.setValue(AVKey.DATASET_NAME, strName);
        params.setValue(AVKey.FORMAT_SUFFIX, ".png");
        params.setValue(AVKey.NUM_LEVELS, intNumLevels);
        params.setValue(AVKey.NUM_EMPTY_LEVELS, 0);
        params.setValue(AVKey.LEVEL_ZERO_TILE_DELTA, new LatLon(Angle.fromDegrees(22.5d), Angle.fromDegrees(45d)));
        params.setValue(AVKey.SECTOR, new MercatorSector(-1.0, 1.0, Angle.NEG180, Angle.POS180));
        params.setValue(AVKey.TILE_URL_BUILDER, new URLBuilder());

        return new LevelSet(params);
    }

    private static class URLBuilder implements TileUrlBuilder {
        private String accessToken = "pk.eyJ1IjoiYWlycG9ydG1hc3RlciIsImEiOiJjand4azM3M2MwMXMzNGFxcjZwMXR5MjJqIn0.rtQ97ju2aDx_TZ_1rl-o7g"; // temp token for testing

        @Override
        public URL getURL(Tile tile, String imageFormat)
                throws MalformedURLException {
            // z/x/y
            return new URL(tile.getLevel().getService()
                    + (tile.getLevelNumber() + 3) + "/" + tile.getColumn() + "/"
                    + ((1 << (tile.getLevelNumber()) + 3) - 1 - tile.getRow())
                    + "?access_token=" + accessToken);
        }
        
    }

    @Override
    public String toString() {
        return strName;
    }

}
